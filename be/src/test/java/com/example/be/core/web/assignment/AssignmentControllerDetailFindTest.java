package com.example.be.core.web.assignment;

import static com.example.be.common.response.ResponseCodeAndMessages.FIND_DETAIL_ASSIGNMENT_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
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
@DisplayName("컨트롤러 테스트 : Assignment 상세 조회")
class AssignmentControllerDetailFindTest extends InitControllerTest {

  @Nested
  @DisplayName("assignment 상세 조회할 때")
  class DetailFindTest {

    @Nested
    @DisplayName("정상적인 요청이라면")
    class NormalTest {

      @Test
      @DisplayName("과제 상세 조회시, 해당 ID를 가진 과제가 조회된다.")
      void find_detail_assignment() throws Exception {
        //given
        Long assignmentId = 1L;
        Long memberId = 1L;

        AssignmentDetailResponse response = new AssignmentDetailResponse(memberId,
            "테스트 과제",
            "테스트 내용",
            "https://dummy_record_adsf_1234",
            LocalDateTime.now(),
            LocalDateTime.of(2023, 2,20,0,0)
            ,Boolean.FALSE);

        BaseResponse<AssignmentDetailResponse> baseResponse = new BaseResponse<>(FIND_DETAIL_ASSIGNMENT_SUCCESS, response);

        when(assignmentService.findById(refEq(memberId), refEq(assignmentId)))
            .thenReturn(response);

        //when
        ResultActions resultActions = mockMvc.perform(get("/assignment/{assignmentId}", assignmentId)
            .header("Authorization", "Bearer "+jwtProvider.generateAccessToken(memberId))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions.andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
            .andDo(print());

        verify(assignmentService).findById(refEq(memberId), refEq(assignmentId));
      }
    }
  }
}
