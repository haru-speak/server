package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class AssignmentSpeakingLogResponse {

  @Schema(type = "Long", description = "과제 스피킹 로그 ID, NOT NULL")
  private Long AssignmentSpeakingLogId;

  @Schema(type = "String", description = "과제 스피킹 로그 제목, NOT NULL")
  private String title;

  @Schema(type = "String", description = "음성 녹음 URL, NOT NULL")
  private String voiceRecord;

  @Schema(type = "String", description = "음성 텍스트 URL, NOT NULL")
  private String voiceText;

  public AssignmentSpeakingLogResponse(Long assignmentSpeakingLogId, String title,
      String voiceRecord, String voiceText) {
    AssignmentSpeakingLogId = assignmentSpeakingLogId;
    this.title = title;
    this.voiceRecord = voiceRecord;
    this.voiceText = voiceText;
  }
}
