package com.example.be.core.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssignmentSubmitRequest {

  @Schema(type = "Long", description = "과제 ID, NOT NULL")
  @NotBlank
  private Long assignmentId;

  @Schema(type = "String", description = "음성 녹음 URL, NOT NULL")
  @NotBlank
  private String voiceRecord;

  public AssignmentSubmitRequest(Long assignmentId, String voiceRecord) {
    this.assignmentId = assignmentId;
    this.voiceRecord = voiceRecord;
  }
}
