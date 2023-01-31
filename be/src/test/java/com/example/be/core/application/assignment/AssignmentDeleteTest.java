package com.example.be.core.application.assignment;

import static com.example.be.common.exception.ErrorCodeAndMessages.ASSIGNMENT_ID_NOT_FOUND_ERROR;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.assignment.NotFoundAssignmentIdException;
import com.example.be.core.application.InitServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("서비스 테스트 : Assignment 삭제")
class AssignmentDeleteTest extends InitServiceTest {

    @Nested
    @DisplayName("과제를 삭제 요청할 때")
    class delete_assignment {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class normal_test {

            @Test
            @DisplayName("삭제 된 과제 아이디는 찾을 수 없다.")
            void delete_assignment() throws Exception {
                //given
                Long assignmentId = 1L;

                //when
                assignmentService.delete(assignmentId);

                //then
                assertThatThrownBy(() -> assignmentService.delete(assignmentId))
                    .isInstanceOf(BaseException.class)
                    .isExactlyInstanceOf(NotFoundAssignmentIdException.class)
                    .hasMessage(ASSIGNMENT_ID_NOT_FOUND_ERROR.getMessage());
            }
        }

    }

}
