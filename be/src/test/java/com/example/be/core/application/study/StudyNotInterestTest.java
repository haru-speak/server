package com.example.be.core.application.study;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.common.exception.study.NotFoundStudyIdException;
import com.example.be.core.application.InitServiceTest;
import com.example.be.core.domain.study.Study;
import com.example.be.core.domain.study.StudyInterest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("서비스 테스트 : Study 찜 취소하기")
public class StudyNotInterestTest extends InitServiceTest {

    @Nested
    @DisplayName("스터디 찜 취소할 때")
    class NotInterestTest {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class NormalTest {

            @Test
            @DisplayName("해당 멤버 ID가 선택한 스터디가 찜 취소된다.")
            void notInterest() throws Exception {
                //given
                Long memberId = 1L;
                Long studyId = 2L;

                Study study = studyRepository.findById(studyId)
                    .orElseThrow(NotFoundStudyIdException::new);
                List<StudyInterest> before = studyInterestRepository.findAll();
                assertThat(before).hasSize(1);

                //when
                studyService.notInterest(memberId, studyId);

                //then
                List<StudyInterest> result = studyInterestRepository.findAll();
                assertThat(result).hasSize(0);
            }
        }
    }
}
