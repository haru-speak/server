package com.example.be.core.application.dto.response;

import com.example.be.core.domain.study.StudyType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudiesResponse {

  @Schema(enumAsRef = true, description = "스피킹 로그 조회 타입, NOT NULL")
  @NotNull
  private StudyType type;

  private List<StudyResponse> studyResponses;

  public StudiesResponse(StudyType type, List<StudyResponse> studyResponses) {
    this.type = type;
    this.studyResponses = studyResponses;
  }
}
