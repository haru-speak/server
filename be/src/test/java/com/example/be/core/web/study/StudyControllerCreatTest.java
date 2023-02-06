package com.example.be.core.web.study;

import static com.example.be.common.response.ResponseCodeAndMessages.CREATE_STUDY_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.dto.request.StudyRequest;
import com.example.be.core.application.dto.response.MemberProfilesResponse;
import com.example.be.core.application.dto.response.StudyDetailResponse;
import com.example.be.core.domain.study.StudyDay;
import com.example.be.core.web.InitControllerTest;
import com.example.be.core.web.StudyController;
import java.util.ArrayList;
import java.util.EnumSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(StudyController.class)
@DisplayName("컨트롤러 테스트 : Study 생성")
class StudyControllerCreatTest extends InitControllerTest {

  @Nested
  @DisplayName("Study 생성할 때")
  class createTest {

    @Nested
    @DisplayName("정상적인 요청이라면")
    class NormalTest {

      @Test
      @DisplayName("스터디 생성시, 해당 멤버 ID를 가진 스터디가 생성된다.")
      void create_study() throws Exception {
        //given
        Long memberId = 1L;
        Integer likeCount = 10;

        StudyRequest request = new StudyRequest("스터디1", "내용1", 3, "english",
            "OPIC", "AL", 5, 1, "대면", "서울", "월,화,수","https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/a4cd3848-b965-4504-90ce-b772398d7f11.jpeg");
        StudyDetailResponse response = new StudyDetailResponse(memberId, "스터디1", "내용1", 3, "english",
            "OPIC", "AL", 5, 1, "대면", "서울","월,화,수", "https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/a4cd3848-b965-4504-90ce-b772398d7f11.jpeg",likeCount, false, Boolean.TRUE, new MemberProfilesResponse("leader", new ArrayList<>()));
        BaseResponse<StudyDetailResponse> baseResponse = new BaseResponse<>(CREATE_STUDY_SUCCESS, response);

        when(studyService.create(refEq(request), refEq(memberId)))
            .thenReturn(response);

        //when
        ResultActions resultActions = mockMvc.perform(post("/study")
            .header("Authorization", "Bearer "+jwtProvider.generateAccessToken(memberId))
            .content(objectMapper.writeValueAsString(request))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions.andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
            .andDo(print());

        verify(studyService).create(refEq(request), refEq(memberId));
      }
    }
  }
}
