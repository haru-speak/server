package com.example.be.core.application.dto.response;

import java.util.List;
import lombok.Getter;

@Getter
public class AssignmentsResponse {

  private final List<AssignmentResponse> assignmentResponses;

  public AssignmentsResponse(List<AssignmentResponse> assignmentResponses) {
    this.assignmentResponses = assignmentResponses;
  }
}
