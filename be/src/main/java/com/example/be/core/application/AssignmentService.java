package com.example.be.core.application;

import com.example.be.core.application.dto.request.AssignmentRequest;
import com.example.be.core.application.dto.response.AssignmentDetailResponse;
import com.example.be.core.application.dto.response.AssignmentResponse;
import com.example.be.core.application.dto.response.AssignmentsResponse;
import com.example.be.core.domain.assignment.AssignmentMember;
import com.example.be.core.repository.assignment.AssignmentMemberRepository;
import com.example.be.core.repository.assignment.AssignmentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class AssignmentService {

  private final AssignmentRepository assignmentRepository;

  private final AssignmentMemberRepository assignmentMemberRepository;

  @Transactional
  public AssignmentDetailResponse create(AssignmentRequest assignmentRequest, Long memberId) {
    log.debug("[과제 생성] assignmentRequest = {}", assignmentRequest);
    return null;
  }

  public AssignmentsResponse find(Long memberId) {
    log.debug("[과제 전체 조회] memberId = {}", memberId);

    List<AssignmentMember> assignmentMembers = assignmentMemberRepository.findAllByMemberId(memberId);
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
    return null;
  }

  @Transactional
  public AssignmentDetailResponse modify(Long assignmentId, AssignmentRequest assignmentRequest) {
    log.debug("[과제 수정] assignmentRequest = {}", assignmentRequest);
    return null;
  }

  @Transactional
  public void delete(Long assignmentId) {
    log.debug("[과제 삭제] assignmentId = {}", assignmentId);
  }


  public AssignmentService(AssignmentRepository assignmentRepository,
      AssignmentMemberRepository assignmentMemberRepository) {
    this.assignmentRepository = assignmentRepository;
    this.assignmentMemberRepository = assignmentMemberRepository;
  }
}
