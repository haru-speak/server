package com.example.be.core.application.study;

import static com.example.be.common.exception.ErrorCodeAndMessages.STUDY_ID_NOT_FOUND_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.study.NotFoundStudyIdException;
import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.StudyService;
import com.example.be.core.application.dto.response.StudyDetailResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@DisplayName("서비스 테스트 : 스터디 상제 조회")
public class StudyFindDetailTest extends InitServiceTest {

    @Autowired
    StudyService studyService;

    @Nested
    @DisplayName("스터디를 상제 조회할 때")
    class study_detail_test {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class normal_test {

            @Test
            @DisplayName("해당 스터디 아이디로 스터디 상세 조회를 성공한다.")
            void find_detail() throws Exception {
                //given
                Long studyId = 1L;

                //when
                StudyDetailResponse response = studyService.findById(studyId);

                //then
                assertThat(response.getTitle()).isEqualTo("study-title1");
                assertThat(response.getContent()).isEqualTo("study-content1");
            }
        }

        @Nested
        @DisplayName("비정상적인 요청이라면")
        class abnormal_test {

            @Test
            @DisplayName("예외 NotFoundStudyIdException 이 발생한다.")
            void fail_find_detail() throws Exception {
                //given
                Long studyId = 987654321L;

                //when & then
                assertThatThrownBy(() -> studyService.findById(studyId))
                    .isInstanceOf(BaseException.class)
                    .isExactlyInstanceOf(NotFoundStudyIdException.class)
                    .hasMessage(STUDY_ID_NOT_FOUND_ERROR.getMessage());
            }
        }

    }

}
