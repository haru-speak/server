package com.example.be.core.application.study;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.dto.request.StudyRequest;
import com.example.be.core.application.dto.response.StudyDetailResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@DisplayName("서비스 테스트 : Study 생성")
class StudyCreateTest extends InitServiceTest {

    @Nested
    @DisplayName("스터디를 생성할 때")
    class create_test {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class normal_test {

            @Test
            @DisplayName("스터디 생성이 성공한다.")
            void create_study() throws Exception {
                //given
                StudyRequest request = new StudyRequest(
                    "study",
                    "study",
                    1,
                    "study",
                    "study",
                    "study",
                    1,
                    "study",
                    1,
                    "study"
                );

                //when
                StudyDetailResponse response = studyService.create(request, 1L);

                //then
                assertThat(response.getTitle()).isEqualTo(request.getTitle());
                assertThat(response.getLevel()).isEqualTo(request.getLevel());
            }
        }

    }

}
