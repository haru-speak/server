package com.example.be.core.web.study;


import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.common.response.ResponseCodeAndMessages;
import com.example.be.core.web.InitControllerTest;
import com.example.be.core.web.StudyController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(StudyController.class)
@DisplayName("컨트롤러 테스트 : Study 찜하기")
public class StudyControllerInterestTest extends InitControllerTest {

    @Nested
    @DisplayName("Study 찜하기할 때")
    class InterestTest {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class NormalTest {

            @Test
            @DisplayName("해당 멤버 ID가 찜한 스터디로 변경된다.")
            void deleteStudyInterested() throws Exception {
                //given
                Long memberId = 1L;
                Long studyId = 1L;
                BaseResponse<Void> baseResponse = new BaseResponse<>(ResponseCodeAndMessages.INTEREST_STUDY_SUCCESS, null);

                //when
                ResultActions resultActions = mockMvc.perform(post("/study/interest/{studyId}", studyId)
                    .header("Authorization", "Bearer " + jwtProvider.generateAccessToken(memberId))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON_VALUE));

                //then
                resultActions.andExpect(status().isOk())
                    .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
                    .andDo(print());

                verify(studyService).interest(refEq(memberId), refEq(studyId));
            }
        }
    }
}
