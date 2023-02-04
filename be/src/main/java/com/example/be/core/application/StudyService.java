package com.example.be.core.application;

import com.example.be.common.exception.speakinglog.NotFoundMemberIdException;
import com.example.be.common.exception.study.NotFoundStudyIdException;
import com.example.be.core.application.dto.request.StudyConditionRequest;
import com.example.be.core.application.dto.request.StudyRequest;
import com.example.be.core.application.dto.response.StudiesResponse;
import com.example.be.core.application.dto.response.StudyDetailResponse;
import com.example.be.core.application.dto.response.StudyResponse;
import com.example.be.core.domain.member.Member;
import com.example.be.core.domain.study.SetStudyDayConverter;
import com.example.be.core.domain.study.Study;
import com.example.be.core.domain.study.StudyMember;
import com.example.be.core.repository.member.MemberRepository;
import com.example.be.core.repository.study.StudyCommentRepository;
import com.example.be.core.repository.study.StudyFavoriteRepository;
import com.example.be.core.repository.study.StudyMemberRepository;
import com.example.be.core.repository.study.StudyRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class StudyService {

  private final StudyRepository studyRepository;
  
  private final StudyFavoriteRepository studyFavoriteRepository;
  
  private final StudyCommentRepository studyCommentRepository;
  
  private final StudyMemberRepository studyMemberRepository;
  
  private final MemberRepository memberRepository;

  private static final Integer ZERO = 0;
  SetStudyDayConverter setStudyDayConverter = new SetStudyDayConverter();

  public StudyService(StudyRepository studyRepository,
      StudyFavoriteRepository studyFavoriteRepository,
      StudyCommentRepository studyCommentRepository,
      StudyMemberRepository studyMemberRepository,
      MemberRepository memberRepository) {
    this.studyRepository = studyRepository;
    this.studyFavoriteRepository = studyFavoriteRepository;
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
        setStudyDayConverter.convertToEntityAttribute(studyRequest.getStudyDay()),
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
        Boolean.FALSE
    );
  }

  public StudiesResponse find(StudyConditionRequest studyConditionRequest) {
    log.debug("스터디 전체 조회 type = {}", studyConditionRequest.toString());

    // 임시 (아직 로그인 구현 X)
    Long loginMemberId = 1L;

    List<Study> studies = studyRepository.findAll();
    List<StudyResponse> studyResponses = studies.stream().map(study ->
        new StudyResponse(
            study.getId(),
            study.getTitle(),
            study.getContent(),
            study.getPosterImage(),
            getStudyLikeCount(study),
            hasStudyFavorite(loginMemberId, study)
        )).collect(Collectors.toList());

    return new StudiesResponse(
        studyConditionRequest.getType(),
        studyResponses
    );
  }

  public StudyDetailResponse findById(Long studyId) {
    log.debug("[스터디 상세 조회] studyId = {}", studyId);

    Study study = studyRepository.findById(studyId)
        .orElseThrow(NotFoundStudyIdException::new);

    // 임시 (아직 로그인 구현 X)
    Long loginMemberId = 1L;

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
        setStudyDayConverter.convertToDatabaseColumn(study.getStudyDay()),
        study.getPosterImage(),
        getStudyLikeCount(study),
        hasStudyFavorite(loginMemberId, study)
    );
  }

  @Transactional
  public StudyDetailResponse modify(Long studyId, StudyRequest studyRequest) {
    log.debug("[스터디 수정] StudyId = {}, StudyRequest = {}", studyId, studyRequest);

    Study study = studyRepository.findById(studyId)
        .orElseThrow(NotFoundStudyIdException::new);

    study.modify(
        studyRequest.getTitle(),
        studyRequest.getContent(),
        studyRequest.getPosterImage(),
        studyRequest.getLanguage(),
        studyRequest.getLevel(),
        setStudyDayConverter.convertToEntityAttribute(studyRequest.getStudyDay()),
        studyRequest.getRule(),
        studyRequest.getRegion(),
        studyRequest.getMaxCapacity(),
        studyRequest.getMinCapacity(),
        studyRequest.getSpeakingTest(),
        studyRequest.getGrade()
    );

    // 임시 (아직 로그인 구현 X)
    Long loginMemberId = 1L;

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
        setStudyDayConverter.convertToDatabaseColumn(study.getStudyDay()),
        study.getPosterImage(),
        getStudyLikeCount(study),
        hasStudyFavorite(loginMemberId, study)
    );
  }

  @Transactional
  public void delete(Long studyId) {
    log.debug("[스터디 삭제] studyId = {}", studyId);

    studyRepository.deleteById(studyId);
  }

  private Integer getStudyLikeCount(Study study) {
    return studyFavoriteRepository.countByStudyId(study.getId());
  }

  private boolean hasStudyFavorite(Long loginMemberId, Study study) {
    return studyFavoriteRepository.findByMemberIdAndStudy(loginMemberId, study).isPresent();
  }
}
