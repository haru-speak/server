package com.example.be.core.application.study;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.InitServiceTest;
import com.example.be.core.domain.study.Study;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("서비스 테스트: Study 삭제")
class StudyDeleteTest extends InitServiceTest {

  @Nested
  @DisplayName("스터디를 삭제할 때")
  class deleteTest {

    @Nested
    @DisplayName("정상적인 요청이라면")
    class NormalTest {

      @Test
      @DisplayName("삭제 전과 삭제 후의 스터디 수 차이가 발생한다.")
      void normal_delete() throws Exception {
        //given
        Long memberId = 1L;
        Long studyId = 1L;
        List<Study> before = studyRepository.findAll();
        assertThat(before.size()).isEqualTo(4);

        //when
        studyService.delete(memberId, studyId);

        //then
        List<Study> after = studyRepository.findAll();
        assertThat(after).size().isEqualTo(3);
      }
    }
  }
}
