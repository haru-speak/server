package com.example.be.core.application;

import com.example.be.common.exception.assignment.NotFoundAssignmentIdException;
import com.example.be.common.exception.speakinglog.NotFoundMemberIdException;
import com.example.be.common.exception.study.NotFoundStudyIdException;
import com.example.be.core.application.dto.request.AssignmentRequest;
import com.example.be.core.application.dto.response.AssignmentDetailResponse;
import com.example.be.core.application.dto.response.AssignmentResponse;
import com.example.be.core.application.dto.response.AssignmentsResponse;
import com.example.be.core.domain.assignment.Assignment;
import com.example.be.core.domain.assignment.AssignmentMember;
import com.example.be.core.domain.member.Member;
import com.example.be.core.domain.study.Study;
import com.example.be.core.domain.study.StudyMember;
import com.example.be.core.repository.assignment.AssignmentMemberRepository;
import com.example.be.core.repository.assignment.AssignmentRepository;
import com.example.be.core.repository.member.MemberRepository;
import com.example.be.core.repository.study.StudyMemberRepository;
import com.example.be.core.repository.study.StudyRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class AssignmentService {

  private final MemberRepository memberRepository;

  private final AssignmentRepository assignmentRepository;

  private final AssignmentMemberRepository assignmentMemberRepository;

  private final StudyRepository studyRepository;

  private final StudyMemberRepository studyMemberRepository;

  @Transactional
  public AssignmentDetailResponse create(AssignmentRequest assignmentRequest, Long loginMemberId) {
    log.debug("[과제 생성] assignmentRequest = {}", assignmentRequest);
    Member member = memberRepository.findById(loginMemberId)
        .orElseThrow(NotFoundMemberIdException::new);

    Study study = studyRepository.findById(assignmentRequest.getStudyId())
        .orElseThrow(NotFoundStudyIdException::new);

    Assignment savedAssignment = assignmentRepository.save(
        new Assignment(
            study,
            assignmentRequest.getTitle(),
            assignmentRequest.getDeadLine(),
            assignmentRequest.getContent(),
            assignmentRequest.getVoiceRecord()
        )
    );

    List<StudyMember> studyMembers = studyMemberRepository.findStudyMembersByStudyId(assignmentRequest.getStudyId());

    studyMembers.forEach(studyMember ->
        assignmentMemberRepository.save(
            new AssignmentMember(
                studyMember.getMember(),
                savedAssignment,
                null,
                "미제출"
            )
        ));


    log.debug("[과제 생성] 성공 : assignment ID = {}", savedAssignment.getId());
    return new AssignmentDetailResponse(
        savedAssignment.getId(),
        savedAssignment.getTitle(),
        savedAssignment.getContent(),
        savedAssignment.getDeadLine(),
        savedAssignment.getVoiceRecord()
    );
  }

  public AssignmentsResponse find(Long memberId) {
    log.debug("[과제 전체 조회] memberId = {}", memberId);

    List<AssignmentMember> assignmentMembers = assignmentMemberRepository.findAssignmentMembersByMemberId(memberId);
    List<AssignmentResponse> assignmentResponses = assignmentMembers.stream().map(assignmentMember ->
        new AssignmentResponse(
            assignmentMember.getAssignment().getId(),
            assignmentMember.getAssignment().getTitle(),
            assignmentMember.getAssignment().getStudy().getId(),
            assignmentMember.getAssignment().getStudy().getTitle(),
            assignmentMember.getAssignment().getDeadLine()
        )).collect(Collectors.toList());
    return new AssignmentsResponse(
        assignmentResponses
    );
  }

  public AssignmentResponse findById(Long assignmentId) {
    log.debug("[과제 개별 조회] assignmentId = {}", assignmentId);

    Assignment assignment = assignmentRepository.findById(assignmentId)
        .orElseThrow(NotFoundAssignmentIdException::new);

    return new AssignmentResponse(
        assignmentId,
        assignment.getTitle(),
        assignment.getStudy().getId(),
        assignment.getStudy().getTitle(),
        assignment.getDeadLine()
    );
  }

  @Transactional
  public AssignmentDetailResponse modify(Long assignmentId, AssignmentRequest assignmentRequest) {
    log.debug("[과제 수정] assignmentId = {} assignmentRequest = {}",assignmentId, assignmentRequest);

    Assignment assignment = assignmentRepository.findById(assignmentId)
        .orElseThrow(NotFoundAssignmentIdException::new);

    assignment.modify(
        assignmentRequest.getTitle(),
        assignmentRequest.getDeadLine(),
        assignmentRequest.getContent(),
        assignmentRequest.getVoiceRecord()
    );

    return new AssignmentDetailResponse(
        assignment.getId(),
        assignment.getTitle(),
        assignment.getContent(),
        assignment.getDeadLine(),
        assignment.getVoiceRecord()
    );
  }

  @Transactional
  public void delete(Long assignmentId) {
    log.debug("[과제 삭제] assignmentId = {}", assignmentId);

    Assignment assignment = assignmentRepository.findById(assignmentId)
        .orElseThrow(NotFoundAssignmentIdException::new);

    assignmentRepository.delete(assignment);
  }


  public AssignmentService(AssignmentRepository assignmentRepository,
      AssignmentMemberRepository assignmentMemberRepository,
      MemberRepository memberRepository, StudyRepository studyRepository,
      StudyMemberRepository studyMemberRepository) {
    this.assignmentRepository = assignmentRepository;
    this.assignmentMemberRepository = assignmentMemberRepository;
    this.memberRepository = memberRepository;
    this.studyRepository = studyRepository;
    this.studyMemberRepository = studyMemberRepository;
  }
}
