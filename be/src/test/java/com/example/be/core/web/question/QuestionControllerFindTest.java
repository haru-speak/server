package com.example.be.core.web.question;

import static com.example.be.common.response.ResponseCodeAndMessages.FIND_QUESTION_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.dto.response.QuestionResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class QuestionControllerFindTest extends InitQuestionControllerTest{

  @Nested
  @DisplayName("오늘의 문장 조회할 때")
  class QuestionFindTest {

    @Nested
    @DisplayName("정상적인 요청이라면")
    class NormalTest {

      @Test
      @DisplayName("오늘의 문장 조회 시, 해당 ID를 가진 오늘의 문장이 조회된다.")
      void find_question() throws Exception {
        //given
        Long questionId = 1L;
        QuestionResponse response = new QuestionResponse(questionId, "어떤 영화를 가장 좋아하세요?", "rulsjaklsjrlkrjslarjsalrkj", "rulsjaklsjrlkrjslarjsalrkj");
        BaseResponse<QuestionResponse> baseResponse = new BaseResponse<>(FIND_QUESTION_SUCCESS, response);

        when(questionService.findById(refEq(questionId)))
            .thenReturn(response);

        //when
        ResultActions resultActions = mockMvc.perform(get("/question")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions.andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));

        verify(questionService).findById(refEq(questionId));
      }
    }
  }

}
