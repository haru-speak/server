package com.example.be.core.domain.study;

import com.example.be.common.exception.study.InvalidStudyTypeException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum StudyType {

  ALL("ALL"),
  MY("MY"),
  MATE("MATE"),
  ;

  private final String type;

  StudyType(String type) {
    this.type = type;
  }

  public static StudyType convert(String source) {

    return Arrays.stream(StudyType.values())
        .filter(e -> e.type.equals(source))
        .findAny()
        .orElseThrow(InvalidStudyTypeException::new);
  }
}
