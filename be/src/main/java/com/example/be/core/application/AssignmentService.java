package com.example.be.core.application;

import com.example.be.core.application.dto.request.AssignmentRequest;
import com.example.be.core.application.dto.response.AssignmentDetailResponse;
import com.example.be.core.application.dto.response.AssignmentResponse;
import com.example.be.core.application.dto.response.AssignmentsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class AssignmentService {

  @Transactional
  public AssignmentDetailResponse create(AssignmentRequest assignmentRequest, Long memberId) {
    log.debug("[과제 생성] assignmentRequest = {}", assignmentRequest);
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

  @Transactional
  public AssignmentDetailResponse modify(Long assignmentId, AssignmentRequest assignmentRequest) {
    log.debug("[과제 수정] assignmentRequest = {}", assignmentRequest);
    return null;
  }

  @Transactional
  public void delete(Long assignmentId) {
    log.debug("[과제 삭제] assignmentId = {}", assignmentId);
  }
}
