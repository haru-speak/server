package com.example.be.core.web.study;

import static com.example.be.common.response.ResponseCodeAndMessages.MODIFY_STUDY_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.StudyService;
import com.example.be.core.application.dto.request.StudyRequest;
import com.example.be.core.application.dto.response.StudyDetailResponse;
import com.example.be.core.web.InitControllerTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class StudyControllerModifyTest extends InitControllerTest {

  @MockBean
  protected StudyService studyService;

  @Nested
  @DisplayName("Study 수정할 때")
  class ModifyTest {

    @Nested
    @DisplayName("정상적인 요청이라면")
    class NormalTest {

      @Test
      @DisplayName("스터디 제목 수정 시, 해당 ID를 가진 스터디의 제목이 수정된다.")
      void modify_study() throws Exception {
        //given
        Long memberId = 1L;
        Long studyId = 1L;
        Integer likeCount = 10;
        StudyRequest request = new StudyRequest("스터디1", "내용1", 3, "english",
            "토익 800점", "toeic", 5, "비대면", 3, "asdzxcv");
        StudyDetailResponse response = new StudyDetailResponse(memberId, "스터디1", "내용1", 3, "english",
            "토익 800점", "toeic", 5, "비대면", 3, "asdzxcv", likeCount, false, new ArrayList<>());
        BaseResponse<StudyDetailResponse> baseResponse = new BaseResponse<>(MODIFY_STUDY_SUCCESS, response);

        when(studyService.modify(refEq(studyId), refEq(request)))
            .thenReturn(response);

        //when
        ResultActions resultActions = mockMvc.perform(put("/study/{studyId}", studyId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        //then
        resultActions.andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
            .andDo(print());

        verify(studyService).modify(refEq(studyId), refEq(request));
      }
    }
  }
}
