package com.example.be.core.application.study;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.dto.request.StudyRequest;
import com.example.be.core.application.dto.response.StudyDetailResponse;
import com.example.be.core.domain.study.StudyType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("서비스 테스트 : Study 수정")
class StudyModifyTest extends InitServiceTest {

  @Nested
  @DisplayName("스터디를 수정할 때")
  class modify_test {

    @Nested
    @DisplayName("정상적인 요청이라면")
    class normal_test {

      @Test
      @DisplayName("스터디의 필드값들이 변경된다.")
      void normal_modify() throws Exception {
        //given
        Long studyId = 1L;
        StudyRequest request = new StudyRequest("수정 제목", "수정 내용", 5, "수정 언어", "수정 목표", "수정 자격증",
            5, 1, "대면", "서울","월화수", "https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/a4cd3848-b965-4504-90ce-b772398d7f11.jpeg");

        //when
        studyService.modify(studyId, request);

        //then
        StudyDetailResponse response = studyService.findById(1L);
        assertThat(response.getTitle()).isEqualTo(request.getTitle());
        assertThat(response.getContent()).isEqualTo(request.getContent());
        assertThat(response.getLevel()).isEqualTo(request.getLevel());
        assertThat(response.getLanguage()).isEqualTo(request.getLanguage());
        assertThat(response.getSpeakingTest()).isEqualTo(request.getSpeakingTest());
        assertThat(response.getGrade()).isEqualTo(request.getGrade());
        assertThat(response.getRule()).isEqualTo(request.getRule());
        assertThat(response.getTimePerWeek()).isEqualTo(request.getTimePerWeek());
        assertThat(response.getPosterImage()).isEqualTo(request.getPosterImage());
      }
    }
  }
}
