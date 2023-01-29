package com.example.be.core.application.assignment;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.AssignmentService;
import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.dto.response.AssignmentsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("서비스 테스트 : 과제 전체 조회")
public class AssignmentFindAllTest extends InitServiceTest {

    @Autowired
    private AssignmentService assignmentService;

    @Nested
    @DisplayName("과제를 전체 조회할 때")
    class find_all_assignment_test {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class normal_test {

            @Test
            @DisplayName("멤버 ID에 맞는 과제들을 전체 조회한다.")
            void find_all() throws Exception {
                //given
                Long memberId = 1L;

                //when
                AssignmentsResponse assignmentsResponse = assignmentService.find(memberId);

                //then
                assertThat(assignmentsResponse.getAssignmentResponses().size()).isEqualTo(3);
            }
        }

    }

}
