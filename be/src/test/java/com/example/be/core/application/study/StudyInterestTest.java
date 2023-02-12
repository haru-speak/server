package com.example.be.core.application.study;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.common.exception.member.NotFoundMemberIdException;
import com.example.be.common.exception.study.NotFoundStudyIdException;
import com.example.be.core.application.InitServiceTest;
import com.example.be.core.domain.member.Member;
import com.example.be.core.domain.study.Study;
import com.example.be.core.domain.study.StudyInterest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("서비스 테스트 : Study 찜하기")
public class StudyInterestTest extends InitServiceTest {

    @Nested
    @DisplayName("스터디 찜하기할 때")
    class InterestTest {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class NormalTest {

            @Test
            @DisplayName("해당 멤버 ID가 선택한 스터디가 찜된다.")
            void interest() throws Exception {
                //given
                Long memberId = 1L;
                Long studyId = 1L;

                Member member = memberRepository.findById(memberId)
                    .orElseThrow(NotFoundMemberIdException::new);
                Study study = studyRepository.findById(studyId)
                    .orElseThrow(NotFoundStudyIdException::new);

                //when
                studyService.interest(memberId,studyId);

                //then
                List<StudyInterest> result = studyInterestRepository.findAll();
                assertThat(result).hasSize(2);
            }
        }
    }
}
