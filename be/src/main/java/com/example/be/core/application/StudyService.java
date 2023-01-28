package com.example.be.core.application;

import com.example.be.core.application.dto.request.StudyConditionRequest;
import com.example.be.core.application.dto.request.StudyRequest;
import com.example.be.core.application.dto.response.StudiesResponse;
import com.example.be.core.application.dto.response.StudyDetailResponse;
import com.example.be.core.application.dto.response.StudyResponse;
import com.example.be.core.domain.study.Study;
import com.example.be.core.repository.study.StudyCommentRepository;
import com.example.be.core.repository.study.StudyFavoriteRepository;
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

  public StudyService(StudyRepository studyRepository,
      StudyFavoriteRepository studyFavoriteRepository,
      StudyCommentRepository studyCommentRepository) {
    this.studyRepository = studyRepository;
    this.studyFavoriteRepository = studyFavoriteRepository;
    this.studyCommentRepository = studyCommentRepository;
  }

  public StudyDetailResponse create(StudyRequest studyRequest, Long memberId) {
    log.debug("[스터디 생성] StudyRequest = {}", studyRequest);
    return null;
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
            studyFavoriteRepository.countByStudyId(study.getId()),
            studyCommentRepository.countByStudyId(study.getId()),
            studyFavoriteRepository.findByMemberIdAndStudy(loginMemberId, study).isPresent()
        )).collect(Collectors.toList());

    return new StudiesResponse(
        studyConditionRequest.getType(),
        studyResponses
    );
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
