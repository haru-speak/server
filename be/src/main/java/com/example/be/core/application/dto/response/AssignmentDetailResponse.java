package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AssignmentDetailResponse {

  @Schema(type = "Long", description = "과제 ID, NOT NULL")
  @NotBlank
  private Long assignmentId;

  @Schema(type = "String", description = "제목, NOT NULL")
  @NotBlank
  private String title;

  @Schema(type = "String", description = "내용, NOT NULL")
  @NotBlank
  private String content;

  @Schema(type = "date", description = "제출 기한, NOT NULL")
  @NotBlank
  private LocalDate deadline;

  @Schema(type = "String", description = "음성 기록 URL, NOT NULL")
  @NotBlank
  private String voiceRecord;

  private AssignmentDetailResponse() {}

  public AssignmentDetailResponse(Long assignmentId, String title, String content,
      LocalDate deadline, String voiceRecord) {
    this.assignmentId = assignmentId;
    this.title = title;
    this.content = content;
    this.deadline = deadline;
    this.voiceRecord = voiceRecord;
  }
}
