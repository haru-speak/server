package com.example.be.core.application.study;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.dto.request.StudyConditionRequest;
import com.example.be.core.application.dto.response.StudiesResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("서비스 테스트 : Study 전체 조회")
class StudyFindAllTest extends InitServiceTest {

  @Nested
  @DisplayName("스터디를 전체 조회할 때")
  class FindAllTest {

    @Nested
    @DisplayName("정상적인 요청이라면")
    class NormalTest {

      @Test
      @DisplayName("전체 스터디가 조회된다.")
      void normal_find_all() {
        //given
        StudyConditionRequest request = new StudyConditionRequest("ALL");
        //when
        StudiesResponse response = studyService.find(request);
        //then
        assertThat(response.getStudyResponses().size()).isEqualTo(4);
      }
    }
  }
}
