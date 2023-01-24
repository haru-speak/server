package com.example.be.core.application;

import com.example.be.core.application.dto.request.StudyRequest;
import com.example.be.core.application.dto.response.StudyDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudyService {

  public StudyDetailResponse create(StudyRequest studyRequest, Long memberId) {
    log.debug("[스터디 생성] StudyRequest = {}", studyRequest);
    return null;
  }
}
