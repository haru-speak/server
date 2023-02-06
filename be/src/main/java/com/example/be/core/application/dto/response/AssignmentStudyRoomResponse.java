package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public class AssignmentStudyRoomResponse {

  @Schema(type = "Long", description = "과제 ID, NOT NULL")
  private final Long assignmentId;

  @Schema(type = "String", description = "제목, NOT NULL")
  private final String title;

  @Schema(type = "String", description = "내용, NOT NULL")
  private final String content;

  @Schema(type = "String", description = "음성 기록 URL, NULL")
  private final String voiceRecord;

  @Schema(type = "String", description = "과제 설명 사진, NULL")
  private final String photo;

  @Schema(type = "date", description = "생성 날짜, NOT NULL")
  private final LocalDateTime createAt;

  @Schema(type = "date", description = "제출 기한, NOT NULL")
  private final LocalDateTime deadLine;

  @Schema(type = "boolean", description = "제출 여부, NOT NULL")
  private final Boolean isSubmitted;

  public AssignmentStudyRoomResponse(Long assignmentId, String title, String content, String voiceRecord,
      String photo, LocalDateTime createAt, LocalDateTime deadLine, Boolean isSubmitted) {
    this.assignmentId = assignmentId;
    this.title = title;
    this.content = content;
    this.voiceRecord = voiceRecord;
    this.photo = photo;
    this.createAt = createAt;
    this.deadLine = deadLine;
    this.isSubmitted = isSubmitted;
  }
}
