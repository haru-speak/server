package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AssignmentResponse {

  @Schema(type = "Long", description = "과제 ID, NOT NULL")
  @NotBlank
  private Long assignmentId;

  @Schema(type = "String", description = "과제 제목, NOT NULL")
  @NotBlank
  private String assignmentTitle;

  @Schema(type = "Long", description = "스터디 ID, NOT NULL")
  @NotBlank
  private Long studyId;

  @Schema(type = "String", description = "스터디 제목, NOT NULL")
  @NotBlank
  private String studyTitle;

  @Schema(type = "date", description = "제출 기한, NOT NULL")
  @NotBlank
  private LocalDate deadline;

  private AssignmentResponse() {}

  public AssignmentResponse(Long assignmentId, String assignmentTitle, Long studyId,
      String studyTitle, LocalDate deadline) {
    this.assignmentId = assignmentId;
    this.assignmentTitle = assignmentTitle;
    this.studyId = studyId;
    this.studyTitle = studyTitle;
    this.deadline = deadline;
  }
}
