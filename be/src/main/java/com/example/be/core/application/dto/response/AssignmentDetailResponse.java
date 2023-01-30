package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AssignmentDetailResponse {

  @Schema(type = "Long", description = "과제 ID, NOT NULL")
  private final Long assignmentId;

  @Schema(type = "String", description = "제목, NOT NULL")
  private final String title;

  @Schema(type = "String", description = "내용, NOT NULL")
  private final String content;

  @Schema(type = "date", description = "제출 기한, NOT NULL")
  private final LocalDateTime deadline;

  @Schema(type = "String", description = "음성 기록 URL, NOT NULL")
  private final String voiceRecord;

  public AssignmentDetailResponse(Long assignmentId, String title, String content,
      LocalDateTime deadline, String voiceRecord) {
    this.assignmentId = assignmentId;
    this.title = title;
    this.content = content;
    this.deadline = deadline;
    this.voiceRecord = voiceRecord;
  }
}
