package com.example.be.core.application;

import com.example.be.core.application.dto.request.StudyRequest;
import com.example.be.core.application.dto.response.StudiesResponse;
import com.example.be.core.application.dto.response.StudyDetailResponse;
import com.example.be.core.domain.study.StudyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudyService {

  public StudyDetailResponse create(StudyRequest studyRequest, Long memberId) {
    log.debug("[스터디 생성] StudyRequest = {}", studyRequest);
    return null;
  }

  public StudiesResponse find(StudyType type) {
    log.debug("스터디 조회 type = {}", type);
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
