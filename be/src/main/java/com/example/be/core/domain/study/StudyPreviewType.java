package com.example.be.core.domain.study;

import com.example.be.common.exception.study.InvalidStudyPreviewTypeException;
import com.example.be.common.exception.study.InvalidStudyTypeException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum StudyPreviewType {

  MY("MY"),
  RANDOM("RANDOM")
  ;

  private final String type;

  StudyPreviewType(String type) {
    this.type = type;
  }

  public static StudyPreviewType convert(String source) {

    return Arrays.stream(StudyPreviewType.values())
        .filter(e -> e.type.equals(source))
        .findAny()
        .orElseThrow(InvalidStudyPreviewTypeException::new);
  }
}
