package com.example.be.core.web.study;

import static com.example.be.common.response.ResponseCodeAndMessages.FIND_DETAIL_STUDY_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.dto.response.StudyDetailResponse;
import com.example.be.core.web.InitControllerTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@DisplayName("컨트롤러 테스트 : 스터디 상세 조회")
class StudyControllerDetailFindTest extends InitControllerTest {

  @Nested
  @DisplayName("Study 상세 조회할 때")
  class DetailFindTest {

    @Nested
    @DisplayName("정상적인 요청이라면")
    class NormalTest {

      @Test
      @DisplayName("스터디 상세 조회시, 해당 ID를 가진 스터디가 조회된다.")
      void find_detail_study_log() throws Exception {
        //given
        Long studyId = 1L;
        Integer likeCount = 10;
        StudyDetailResponse response = new StudyDetailResponse(studyId, "스터디 1", "스터디 1 입니다", 3, "english",
            "OPIC", "AL", 5, 1, "대면", "월화수", "https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/a4cd3848-b965-4504-90ce-b772398d7f11.jpeg",likeCount, true, new ArrayList<>());
        BaseResponse<StudyDetailResponse> baseResponse = new BaseResponse<>(
            FIND_DETAIL_STUDY_SUCCESS, response);

        when(studyService.findById(studyId))
            .thenReturn(response);

        //when
        ResultActions resultActions = mockMvc.perform(get("/study/{studyId}", studyId)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions.andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
            .andDo(print());

        verify(studyService).findById(refEq(studyId));
      }
    }
  }
}
