package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AssignmentResponse {

  @Schema(type = "Long", description = "과제 ID, NOT NULL")
  private final Long assignmentId;

  @Schema(type = "String", description = "과제 제목, NOT NULL")
  private final String title;

  @Schema(type = "String", description = "과제 내용, NOT NULL")
  private final String content;

  @Schema(type = "String", description = "음성 기록 URL, NULL")
  private final String voiceRecord;

  @Schema(type = "String", description = "과제 설명 사진, NULL")
  private final String photo;

  @Schema(type = "date", description = "과제 제출 기한, NOT NULL")
  private final LocalDateTime deadLine;

  public AssignmentResponse(Long assignmentId, String title, String content,
      String voiceRecord, String photo, LocalDateTime deadLine) {
    this.assignmentId = assignmentId;
    this.title = title;
    this.deadLine = deadLine;
    this.content = content;
    this.voiceRecord = voiceRecord;
    this.photo = photo;
  }
}
