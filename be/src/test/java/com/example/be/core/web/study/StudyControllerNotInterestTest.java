package com.example.be.core.web.study;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
@DisplayName("컨트롤러 테스트 : Study 찜 취소하기")
public class StudyControllerNotInterestTest extends InitControllerTest {

    @Nested
    @DisplayName("Study 찜 취소할 때")
    class NotInterestTest {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class NormalTest {

            @Test
            @DisplayName("해당 멤버 ID가 찜 취소한 스터디로 변경된다.")
            void deleteStudyInterested() throws Exception {
                //given
                Long memberId = 1L;
                Long studyId = 1L;
                BaseResponse<Void> baseResponse = new BaseResponse<>(ResponseCodeAndMessages.NOT_INTEREST_STUDY_SUCCESS, null);

                //when
                ResultActions resultActions = mockMvc.perform(delete("/study/interest/{studyId}", studyId)
                    .header("Authorization", "Bearer " + jwtProvider.generateAccessToken(memberId))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON_VALUE));

                //then
                resultActions.andExpect(status().isOk())
                    .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
                    .andDo(print());
            }
        }
    }
}
