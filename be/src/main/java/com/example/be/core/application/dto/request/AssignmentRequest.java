package com.example.be.core.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssignmentRequest {

  @NotNull(message = "스터디 ID가 빈 값이어서는 안됩니다.")
  @Schema(type = "Long", description = "스터디 ID")
  private Long studyId;

  @NotNull(message = "제목이 빈 값이어서는 안됩니다.")
  @Size(min = 4, max = 30, message = "제목은 4자 이상 ~ 30자 이내로 작성해야 합니다.")
  @Schema(type = "String", description = "제목, 4자 이상 ~ 30자 이내")
  private String title;

  @NotNull(message = "제출 기한이 빈 값이어서는 안됩니다.")
  @Schema(type = "date", description = "제출 기한, NOT NULL")
  private LocalDateTime deadLine;

  @NotNull(message = "내용이 빈 값이어서는 안됩니다.")
  @Size(min = 4, max = 100, message = "내용은 4자 이상 ~ 100자 이내로 작성해야 합니다.")
  @Schema(type = "String", description = "내용, NOT NULL")
  private String content;

  @URL(message = "올바른 voiceRecord를 입력해야 합니다.")
  @Schema(type = "String", description = "음성 기록 URL, NULL")
  private String voiceRecord;

  @Size(min = 2, message = "올바른 사진 URL을 입력해야 합니다.")
  @Schema(type = "String", description = "과제 설명 사진, NULL")
  private String photo;

  public AssignmentRequest(Long studyId, String title, LocalDateTime deadLine, String content,
      String voiceRecord, String photo) {
    this.studyId = studyId;
    this.title = title;
    this.deadLine = deadLine;
    this.content = content;
    this.voiceRecord = voiceRecord;
    this.photo = photo;
  }
}
