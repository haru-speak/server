package com.example.be.core.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssignmentRequest {

  @Schema(type = "Long", description = "스터디 ID, NOT NULL")
  @NotBlank
  private Long studyId;

  @Schema(type = "String", description = "제목, NOT NULL")
  @NotBlank
  private String title;

  @Schema(type = "String", description = "내용, NOT NULL")
  @NotBlank
  private String content;

  @Schema(type = "date", description = "날짜, NOT NULL")
  @NotBlank
  private LocalDate date;

  @Schema(type = "String", description = "음성 기록 URL, NOT NULL")
  @NotBlank
  private String voiceRecord;

  public AssignmentRequest(Long studyId, String title, String content, LocalDate date,
      String voiceRecord) {
    this.studyId = studyId;
    this.title = title;
    this.content = content;
    this.date = date;
    this.voiceRecord = voiceRecord;
  }
}
