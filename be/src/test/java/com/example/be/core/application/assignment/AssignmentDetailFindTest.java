package com.example.be.core.application.assignment;

import static com.example.be.common.exception.ErrorCodeAndMessages.ASSIGNMENT_ID_NOT_FOUND_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.assignment.NotFoundAssignmentIdException;
import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.dto.response.AssignmentResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("서비스 테스트 : Assignment 상세 조회")
class AssignmentDetailFindTest extends InitServiceTest {

    @Nested
    @DisplayName("과제 상세 조회할 때")
    class detail_find {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class normal_test {

            @Test
            @DisplayName("과제 상세 조회를 성공한다.")
            void detail_find_test() throws Exception {
                //given
                Long assignmentId = 1L;

                //when
                AssignmentResponse response = assignmentService.findById(assignmentId);

                //then
                assertThat(response.getAssignmentTitle()).isEqualTo("assignment-title1");
                assertThat(response.getStudyTitle()).isEqualTo("study-title1");
            }
        }

        @Nested
        @DisplayName("잘못된 과제 아이디로 조회를 하면")
        class abnormal_test {

            @Test
            @DisplayName("ASSIGNMENT_ID_NOT_FOUND_ERROR 예외를 보낸다.")
            void fail_detail_find_test() throws Exception {
                //given
                Long assignmentId = 987654321L;

                //when & then
                assertThatThrownBy(() -> assignmentService.findById(assignmentId))
                    .isInstanceOf(BaseException.class)
                    .isExactlyInstanceOf(NotFoundAssignmentIdException.class)
                    .hasMessage(ASSIGNMENT_ID_NOT_FOUND_ERROR.getMessage());
            }
        }

    }

}
