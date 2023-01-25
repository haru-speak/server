package com.example.be.core.application;

import com.example.be.core.application.dto.request.AssignmentRequest;
import com.example.be.core.application.dto.response.AssignmentDetailResponse;
import com.example.be.core.application.dto.response.AssignmentResponse;
import com.example.be.core.application.dto.response.AssignmentsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AssignmentService {

  public AssignmentDetailResponse create(AssignmentRequest assignmentRequest, Long memberId) {
    log.debug("[과제 생성] StudyRequest = {}", assignmentRequest);
    return null;
  }


  public AssignmentsResponse find(Long memberId) {
    log.debug("[과제 전체 조회] memberId = {}", memberId);
    return null;
  }

  public AssignmentResponse findById(Long assignmentId) {
    log.debug("[과제 개별 조회] assignmentId = {}", assignmentId);
    return null;
  }
}
