package com.example.be.core.application.dto.request;

import com.example.be.core.domain.study.StudyPreviewType;
import com.example.be.core.domain.study.StudyType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudyPreviewConditionRequest {

  @Schema(enumAsRef = true, description = "스터디 미리보기 조회 타입, NULL")
  private StudyPreviewType type;

  public StudyPreviewConditionRequest(String type) {
    this.type = getStudyPreviewTypeFromString(type);
  }

  private StudyPreviewType getStudyPreviewTypeFromString(String input) {

    if (input == null) {
      throw new IllegalArgumentException("type이 정해지지 않았습니다.");
    }
    return StudyPreviewType.convert(input.toUpperCase());
  }

}
