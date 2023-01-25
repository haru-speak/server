package com.example.be.core.application.dto.response;

import com.example.be.core.domain.speakinglog.SpeakingLogType;
import java.util.List;
import lombok.Getter;

@Getter
public class AssignmentsResponse {

  // 스터디에 따른 과제 보기 등 기능 추가시 enum 타입 작성
  private final List<AssignmentResponse> assignmentResponses;

  public AssignmentsResponse(List<AssignmentResponse> assignmentResponses) {
    this.assignmentResponses = assignmentResponses;
  }
}
