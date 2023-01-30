package com.example.be.core.web.study;

import static com.example.be.common.response.ResponseCodeAndMessages.DELETE_STUDY_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.StudyService;
import com.example.be.core.web.InitControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class StudyControllerDeleteTest extends InitControllerTest {

  @MockBean
  protected StudyService studyService;

  @Nested
  @DisplayName("Study 삭제할 때")
  class DeleteTest {

    @Nested
    @DisplayName("정상적인 요청이라면")
    class NormalTest {

      @Test
      @DisplayName("스터디 삭제시 해당 ID를 가진 스터디가 삭제된다")
      void delete_study_log() throws Exception {
        //given
        Long studyId = 1L;
        BaseResponse<Void> baseResponse = new BaseResponse<>(DELETE_STUDY_SUCCESS, null);

        //when
        ResultActions resultActions = mockMvc.perform(delete("/study/{studyId}", studyId)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions.andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
            .andDo(print());

        verify(studyService).delete(refEq(studyId));
      }
    }
  }
}
