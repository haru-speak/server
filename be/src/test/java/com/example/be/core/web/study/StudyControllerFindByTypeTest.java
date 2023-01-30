package com.example.be.core.web.study;

import static com.example.be.common.response.ResponseCodeAndMessages.FIND_ALL_STUDY_SUCCESS;
import static com.example.be.core.domain.study.StudyType.ALL;
import static com.example.be.core.domain.study.StudyType.MY;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.StudyService;
import com.example.be.core.application.dto.request.StudyConditionRequest;
import com.example.be.core.application.dto.response.StudiesResponse;
import com.example.be.core.web.InitControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

class StudyControllerFindByTypeTest extends InitControllerTest {

  @MockBean
  protected StudyService studyService;

  @Nested
  @DisplayName("Study를 조회할 때")
  class findTest {

    @Nested
    @DisplayName("정상적인 요청이라면")
    class NormalTest {

      @Test
      @DisplayName("ALL TYPE 스터디 조회시 ALL TYPE 스터디가 조회된다")
      void find_all_type_study() throws Exception {
        //given
        StudyConditionRequest request = new StudyConditionRequest("all");
        StudiesResponse response = new StudiesResponse(ALL, null);
        BaseResponse<StudiesResponse> baseResponse = new BaseResponse<>(FIND_ALL_STUDY_SUCCESS, response);

        when(studyService.find(refEq(request)))
            .thenReturn(response);

        //when
        ResultActions resultActions = mockMvc.perform(get("/study?type=all")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions.andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
            .andDo(print());

        verify(studyService).find(refEq(request));
      }

      @Test
      @DisplayName("타입 없이 스터디 조회시 MY TYPE 스터디가 조회된다")
      void find_no_type_study() throws Exception {
        //given
        StudyConditionRequest request = new StudyConditionRequest(null);
        StudiesResponse response = new StudiesResponse(MY, null);
        BaseResponse<StudiesResponse> baseResponse = new BaseResponse<>(FIND_ALL_STUDY_SUCCESS, response);

        when(studyService.find(refEq(request)))
            .thenReturn(response);

        //when
        ResultActions resultActions = mockMvc.perform(get("/study")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions.andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
            .andDo(print());

        verify(studyService).find(refEq(request));
      }
    }
  }

}
