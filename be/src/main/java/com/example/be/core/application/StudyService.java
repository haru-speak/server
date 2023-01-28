package com.example.be.core.application;

import com.example.be.common.exception.speakinglog.NotFoundMemberIdException;
import com.example.be.core.application.dto.request.StudyConditionRequest;
import com.example.be.core.application.dto.request.StudyRequest;
import com.example.be.core.application.dto.response.StudiesResponse;
import com.example.be.core.application.dto.response.StudyDetailResponse;
import com.example.be.core.domain.member.Member;
import com.example.be.core.domain.study.Study;
import com.example.be.core.domain.study.StudyMember;
import com.example.be.core.repository.member.MemberRepository;
import com.example.be.core.repository.study.StudyMemberRepository;
import com.example.be.core.repository.study.StudyRepository;
import java.util.ArrayList;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class StudyService {

  private static final Integer ZERO = 0;
  private final StudyRepository studyRepository;
  private final StudyMemberRepository studyMemberRepository;
  private final MemberRepository memberRepository;

  public StudyService(StudyRepository studyRepository, StudyMemberRepository studyMemberRepository,
      MemberRepository memberRepository) {
    this.studyRepository = studyRepository;
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
        studyRequest.getTimePerWeek(),
        studyRequest.getRule(),
        studyRequest.getCapacity(),
        studyRequest.getGoal(),
        studyRequest.getCertificate()
    );
    studyRepository.save(study);

    studyMemberRepository.save(
        new StudyMember(
            member,
            study
        )
    );

    return new StudyDetailResponse(
        study.getId(),
        study.getTitle(),
        study.getContent(),
        study.getLevel(),
        study.getLanguage(),
        study.getGoal(),
        study.getCertificate(),
        study.getCapacity(),
        study.getRule(),
        study.getTimePerWeek(),
        study.getPosterImage(),
        ZERO,
        Boolean.FALSE,
        new ArrayList<>()
    );
  }

  public StudiesResponse find(StudyConditionRequest studyConditionRequest) {
    log.debug("스터디 조회 type = {}", studyConditionRequest.toString());
    return null;
  }

  public StudyDetailResponse findById(Long studyId) {
    log.debug("[스터디 상세 조회] studyId = {}", studyId);
    return null;
  }

  public StudyDetailResponse modify(Long studyId, StudyRequest studyRequest) {
    log.debug("[스터디 수정] StudyRequest = {}", studyRequest);
    return null;
  }

  public void delete(Long studyId) {
    log.debug("[스터디 삭제] studyId = {}", studyId);
  }
}
