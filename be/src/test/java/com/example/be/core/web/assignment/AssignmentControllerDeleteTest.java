package com.example.be.core.web.assignment;

import static com.example.be.common.response.ResponseCodeAndMessages.DELETE_ASSIGNMENT_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.web.AssignmentController;
import com.example.be.core.web.InitControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(AssignmentController.class)
@DisplayName("컨트롤러 테스트 : 과제 삭제")
class AssignmentControllerDeleteTest extends InitControllerTest {

  @Nested
  @DisplayName("Assignment 삭제할 때")
  class DeleteTest {

    @Nested
    @DisplayName("정상적인 요청이라면")
    class NormalTest {

      @Test
      @DisplayName("과제 삭제시 해당 ID를 가진 과제가 삭제된다")
      void delete_assignment() throws Exception {

        //given
        Long memberId = 1L;
        Long assignmentId = 1L;
        BaseResponse<Void> baseResponse = new BaseResponse<>(DELETE_ASSIGNMENT_SUCCESS, null);

        //when
        ResultActions resultActions = mockMvc.perform(delete("/assignment/{assignmentId}", assignmentId)
            .header("Authorization", "Bearer "+jwtProvider.generateAccessToken(memberId))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions.andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
            .andDo(print());

        verify(assignmentService).delete(refEq(memberId), refEq(assignmentId));
      }
    }
  }
}
