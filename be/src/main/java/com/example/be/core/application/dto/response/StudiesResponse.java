package com.example.be.core.application.dto.response;

import java.util.List;
import lombok.Getter;

@Getter
public class StudiesResponse {

  //검색 기능 구현시 리턴 타입을 관리하는 enum 추가
  private final List<StudyResponse> studyResponses;

  public StudiesResponse(List<StudyResponse> studyResponses) {
    this.studyResponses = studyResponses;
  }
}
