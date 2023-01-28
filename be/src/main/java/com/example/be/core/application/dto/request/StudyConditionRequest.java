package com.example.be.core.application.dto.request;

import com.example.be.core.domain.study.StudyType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudyConditionRequest {

  @Schema(enumAsRef = true, description = "스터디 로그 조회 타입, NULL, default = type.my")
  private StudyType type;

  public StudyConditionRequest(String type) {
    this.type = getStudyTypeFromString(type);
  }

  private StudyType getStudyTypeFromString(String input) {

    if (input == null) {
      return StudyType.MY;
    }
    return StudyType.convert(input.toUpperCase());
  }

}
