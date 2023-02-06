package com.example.be.core.application;

import com.example.be.common.exception.member.NotFoundMemberIdException;
import com.example.be.common.exception.study.NotFoundStudyIdException;
import com.example.be.common.exception.study.NotMatchStudyAndMemberException;
import com.example.be.core.application.dto.request.StudyConditionRequest;
import com.example.be.core.application.dto.request.StudyRequest;
import com.example.be.core.application.dto.response.MemberProfilesResponse;
import com.example.be.core.application.dto.response.StudiesResponse;
import com.example.be.core.application.dto.response.StudyDetailResponse;
import com.example.be.core.application.dto.response.StudyResponse;
import com.example.be.core.domain.member.Member;
import com.example.be.core.domain.study.StudyDayConverter;
import com.example.be.core.domain.study.Study;
import com.example.be.core.domain.study.StudyMember;
import com.example.be.core.repository.member.MemberRepository;
import com.example.be.core.repository.study.StudyCommentRepository;
import com.example.be.core.repository.study.StudyInterestRepository;
import com.example.be.core.repository.study.StudyMemberRepository;
import com.example.be.core.repository.study.StudyRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class StudyService {

  private final StudyRepository studyRepository;
  
  private final StudyInterestRepository studyInterestRepository;
  
  private final StudyCommentRepository studyCommentRepository;
  
  private final StudyMemberRepository studyMemberRepository;
  
  private final MemberRepository memberRepository;

  private static final Integer ZERO = 0;
  StudyDayConverter studyDayConverter = new StudyDayConverter();

  public StudyService(StudyRepository studyRepository,
      StudyInterestRepository studyInterestRepository,
      StudyCommentRepository studyCommentRepository,
      StudyMemberRepository studyMemberRepository,
      MemberRepository memberRepository) {
    this.studyRepository = studyRepository;
    this.studyInterestRepository = studyInterestRepository;
    this.studyCommentRepository = studyCommentRepository;
    this.studyMemberRepository = studyMemberRepository;
    this.memberRepository = memberRepository;
  }

  @Transactional
  public StudyDetailResponse create(StudyRequest studyRequest, Long memberId) {
    log.debug("[스터디 생성] StudyRequest = {}", studyRequest);

    Member member = memberRepository.findById(memberId)
        .orElseThrow(NotFoundMemberIdException::new);

    Study study = new Study(
        studyRequest.getTitle(),
        studyRequest.getContent(),
        studyRequest.getPosterImage(),
        studyRequest.getLanguage(),
        studyRequest.getLevel(),
        studyDayConverter.convertToEntityAttribute(studyRequest.getStudyDay()),
        studyRequest.getRule(),
        studyRequest.getRegion(),
        studyRequest.getMinCapacity(),
        studyRequest.getMaxCapacity(),
        studyRequest.getSpeakingTest(),
        studyRequest.getGrade()
    );
    studyRepository.save(study);

    studyMemberRepository.save(
        new StudyMember(
            member,
            study,
            Boolean.TRUE
        )
    );

    MemberProfilesResponse memberProfilesResponse = new MemberProfilesResponse(
        member.getProfileImage(),
        new ArrayList<>()
    );

    return new StudyDetailResponse(
        study.getId(),
        study.getTitle(),
        study.getContent(),
        study.getLevel(),
        study.getLanguage(),
        study.getSpeakingTest(),
        study.getGrade(),
        study.getMaxCapacity(),
        study.getMinCapacity(),
        study.getRule(),
        studyRequest.getRegion(),
        study.getStudyDay().toString(),
        study.getPosterImage(),
        ZERO,
        Boolean.FALSE,
        Boolean.TRUE,
        memberProfilesResponse
    );
  }

  public StudiesResponse find(Long memberId, StudyConditionRequest studyConditionRequest) {
    log.debug("스터디 전체 조회 type = {}", studyConditionRequest.toString());

    List<Study> studies = studyRepository.findAll();
    List<StudyResponse> studyResponses = studies.stream().map(study -> {
      Integer memberCount = studyMemberRepository.countStudyMemberByStudyId(study.getId());
      return new StudyResponse(
          study.getId(),
          study.getTitle(),
          study.getContent(),
          study.getMaxCapacity(),
          study.getMinCapacity(),
          study.getPosterImage(),
          memberCount,
          isStudyInterested(memberId, study),
          study.getCreatedAt()
      );
    }).collect(Collectors.toList());

    return new StudiesResponse(
        studyConditionRequest.getType(),
        studyResponses
    );
  }

  public StudyDetailResponse findById(Long memberId, Long studyId) {
    log.debug("[스터디 상세 조회] studyId = {}", studyId);

    Study study = getStudy(studyId);

    String leaderImg = "";
    List<String> otherImg = new ArrayList<>();
    boolean isLeader = Boolean.FALSE;

    List<StudyMember> members = getStudyMembers(studyId);

    for (StudyMember member : members) {
      if (member.getLeader().equals(Boolean.TRUE)) {
        leaderImg = member.getMember().getProfileImage();
        isLeader = leaderCheck(memberId, isLeader, member);
      }else{
        otherImg.add(member.getMember().getProfileImage());
      }
    }

    MemberProfilesResponse memberProfilesResponse = new MemberProfilesResponse(
        leaderImg,
        otherImg
    );

    return new StudyDetailResponse(
        study.getId(),
        study.getTitle(),
        study.getContent(),
        study.getLevel(),
        study.getLanguage(),
        study.getSpeakingTest(),
        study.getGrade(),
        study.getMaxCapacity(),
        study.getMinCapacity(),
        study.getRule(),
        study.getRegion(),
        studyDayConverter.convertToDatabaseColumn(study.getStudyDay()),
        study.getPosterImage(),
        getStudyInterestCount(study),
        isStudyInterested(memberId, study),
        isLeader,
        memberProfilesResponse
    );
  }

  @Transactional
  public StudyDetailResponse modify(Long memberId, Long studyId, StudyRequest studyRequest) {
    log.debug("[스터디 수정] StudyId = {}, StudyRequest = {}", studyId, studyRequest);

    Study study = getStudy(studyId);

    String leaderImg = "";
    List<String> otherImg = new ArrayList<>();
    boolean isLeader = Boolean.FALSE;

    List<StudyMember> members = getStudyMembers(studyId);

    for (StudyMember member : members) {
      if (member.getLeader().equals(Boolean.TRUE)) {
        leaderImg = member.getMember().getProfileImage();
        isLeader = leaderCheck(memberId, isLeader, member);
      }else{
        otherImg.add(member.getMember().getProfileImage());
      }
    }

    MemberProfilesResponse memberProfilesResponse = new MemberProfilesResponse(
        leaderImg,
        otherImg
    );

    study.modify(
        studyRequest.getTitle(),
        studyRequest.getContent(),
        studyRequest.getPosterImage(),
        studyRequest.getLanguage(),
        studyRequest.getLevel(),
        studyDayConverter.convertToEntityAttribute(studyRequest.getStudyDay()),
        studyRequest.getRule(),
        studyRequest.getRegion(),
        studyRequest.getMaxCapacity(),
        studyRequest.getMinCapacity(),
        studyRequest.getSpeakingTest(),
        studyRequest.getGrade()
    );



    return new StudyDetailResponse(
        study.getId(),
        study.getTitle(),
        study.getContent(),
        study.getLevel(),
        study.getLanguage(),
        study.getSpeakingTest(),
        study.getGrade(),
        study.getMaxCapacity(),
        study.getMinCapacity(),
        study.getRule(),
        study.getRegion(),
        studyDayConverter.convertToDatabaseColumn(study.getStudyDay()),
        study.getPosterImage(),
        getStudyInterestCount(study),
        isStudyInterested(memberId, study),
        isLeader,
        memberProfilesResponse
    );
  }

  @Transactional
  public void delete(Long memberId, Long studyId) {
    log.debug("[스터디 삭제] studyId = {}", studyId);

    StudyMember studyLeader = studyMemberRepository.findStudyMemberByStudyIdAndLeaderIsTrue(studyId);

    if (!Objects.equals(studyLeader.getMember().getId(), memberId)) {
      throw new NotMatchStudyAndMemberException();
    }

    studyRepository.deleteById(studyId);
  }

  private Integer getStudyInterestCount(Study study) {
    return studyInterestRepository.countByStudyId(study.getId());
  }

  private boolean isStudyInterested(Long loginMemberId, Study study) {
    return studyInterestRepository.findByMemberIdAndStudy(loginMemberId, study).isPresent();
  }

  private static boolean leaderCheck(Long loginMemberId, boolean isLeader, StudyMember member) {
    if (loginMemberId.equals(member.getId())){
      isLeader = Boolean.TRUE;
    }
    return isLeader;
  }

  private Study getStudy(Long studyId) {
    return studyRepository.findById(studyId)
        .orElseThrow(NotFoundStudyIdException::new);
  }

  private List<StudyMember> getStudyMembers(Long studyId) {
    List<StudyMember> members = studyMemberRepository.findStudyMembersByStudyId(
        studyId);
    return members;
  }
}
