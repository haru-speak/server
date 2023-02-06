package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public class AssignmentStudyResponse {

  @Schema(type = "Long", description = "과제 ID, NOT NULL")
  private final Long assignmentId;

  @Schema(type = "String", description = "과제 제목, NOT NULL")
  private final String assignmentTitle;

  @Schema(type = "Long", description = "스터디 ID, NOT NULL")
  private final Long studyId;

  @Schema(type = "String", description = "스터디 제목, NULL")
  private final String studyTitle;

  @Schema(type = "date", description = "과제 제출 기한, NOT NULL")
  private final LocalDateTime deadLine;

  public AssignmentStudyResponse(Long assignmentId, String assignmentTitle, Long studyId,
      String studyTitle, LocalDateTime deadLine) {
    this.assignmentId = assignmentId;
    this.assignmentTitle = assignmentTitle;
    this.studyId = studyId;
    this.studyTitle = studyTitle;
    this.deadLine = deadLine;
  }
}
