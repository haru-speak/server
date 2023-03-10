package com.example.be.core.web.study;

import static com.example.be.common.response.ResponseCodeAndMessages.FIND_ALL_STUDY_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.FIND_INTEREST_STUDY_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.FIND_POPULAR_STUDY_SUCCESS;
import static com.example.be.core.domain.study.StudyType.INTEREST;
import static com.example.be.core.domain.study.StudyType.POPULAR;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.dto.request.StudyConditionRequest;
import com.example.be.core.application.dto.response.StudiesResponse;
import com.example.be.core.web.InitControllerTest;
import com.example.be.core.web.StudyController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(StudyController.class)
@DisplayName("컨트롤러 테스트 : Study 전체 조회")
class StudyControllerFindByTypeTest extends InitControllerTest {

  @Nested
  @DisplayName("Study를 조회할 때")
  class findTest {

    @Nested
    @DisplayName("정상적인 요청이라면")
    class NormalTest {

      @Test
      @DisplayName("POPULAR TYPE 스터디 조회시 POPULAR TYPE 스터디가 조회된다")
      void find_all_type_study() throws Exception {
        //given
        Long memberId = 1L;
        StudyConditionRequest request = new StudyConditionRequest("POPULAR");
        StudiesResponse response = new StudiesResponse(POPULAR, null);
        BaseResponse<StudiesResponse> baseResponse = new BaseResponse<>(FIND_ALL_STUDY_SUCCESS, response);

        when(studyService.find(refEq(memberId), refEq(request)))
            .thenReturn(response);

        //when
        ResultActions resultActions = mockMvc.perform(get("/study?type=popular")
            .header("Authorization", "Bearer "+jwtProvider.generateAccessToken(memberId))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions.andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
            .andDo(print());

        verify(studyService).find(refEq(memberId), refEq(request));
      }

      @Test
      @DisplayName("INTEREST TYPE 스터디 조회시 INTEREST TYPE 스터디가 조회된다")
      void find_no_type_study() throws Exception {
        //given
        Long memberId = 1L;
        StudyConditionRequest request = new StudyConditionRequest("interest");
        StudiesResponse response = new StudiesResponse(INTEREST, null);
        BaseResponse<StudiesResponse> baseResponse = new BaseResponse<>(FIND_ALL_STUDY_SUCCESS, response);

        when(studyService.find(refEq(memberId), refEq(request)))
            .thenReturn(response);

        //when
        ResultActions resultActions = mockMvc.perform(get("/study?type=interest")
            .header("Authorization", "Bearer "+jwtProvider.generateAccessToken(memberId))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions.andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
            .andDo(print());

        verify(studyService).find(refEq(memberId), refEq(request));
      }
    }
  }

}
