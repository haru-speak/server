package com.example.be.core.web.assignment;

import static com.example.be.common.response.ResponseCodeAndMessages.MODIFY_ASSIGNMENT_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.dto.request.AssignmentModifyRequest;
import com.example.be.core.application.dto.response.AssignmentDetailResponse;
import com.example.be.core.web.AssignmentController;
import com.example.be.core.web.InitControllerTest;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(AssignmentController.class)
@DisplayName("컨트롤러 테스트 : Assignment 수정")
class AssignmentControllerModifyTest extends InitControllerTest {

  @Nested
  @DisplayName("Assignment 수정할 때")
  class ModifyTest {

    @Nested
    @DisplayName("정상적인 요청이라면")
    class NormalTest {

      @Test
      @DisplayName("과제 제목 수정 시, 해당 ID를 과제의 제목이 수정된다.")
      void modify_assignment() throws Exception {
        //given
        Long assignmentId = 1L;
        Long memberId = 1L;

        AssignmentModifyRequest request = new AssignmentModifyRequest(
            "수정할 제목",
            LocalDateTime.of(2022,2,8,0,0),
            "테스트 내용",
            "https://dummy-record-abcd-4321",
            "dummy-text-abcd-4321"
        );

        AssignmentDetailResponse response = new AssignmentDetailResponse(
            assignmentId,
            "수정할 제목",
            "테스트 내용",
            "https://dummy-record-abcd-4321",
            LocalDateTime.now(),
            LocalDateTime.of(2022,2,8,0,0),
            Boolean.FALSE
        );

        BaseResponse<AssignmentDetailResponse> baseResponse = new BaseResponse<>(MODIFY_ASSIGNMENT_SUCCESS, response);

        when(assignmentService.modify(refEq(memberId), refEq(assignmentId), refEq(request)))
            .thenReturn(response);

        //when
        ResultActions resultActions = mockMvc.perform(
            put("/assignment/{assignmentId}", assignmentId)
                .header("Authorization", "Bearer "+jwtProvider.generateAccessToken(memberId))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        //then
        resultActions.andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
            .andDo(print());

        verify(assignmentService).modify(refEq(memberId), refEq(assignmentId), refEq(request));
      }
    }
  }
}
