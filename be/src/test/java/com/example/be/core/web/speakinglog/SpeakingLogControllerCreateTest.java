package com.example.be.core.web.speakinglog;

import static com.example.be.common.response.ResponseCodeAndMessages.CREATE_SPEAKING_LOG_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.application.dto.request.SpeakingLogRequest;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import com.example.be.core.web.InitControllerTest;
import com.example.be.core.web.SpeakingLogController;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(SpeakingLogController.class)
public class SpeakingLogControllerCreateTest extends InitControllerTest {

    @MockBean
    protected SpeakingLogService speakingLogService;
    @Nested
    @DisplayName("Speaking Log 생성할 때")
    class createTest {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class NormalTest {

            @Test
            @DisplayName("스프킹 로그 생성시, 해당 멤버 ID를 가진 스피킹 로그가 생성된다.")
            void create_speaking_log() throws Exception {
                //given
                Long memberId = 1L;
                Integer likeCount = 10;
                SpeakingLogRequest request = new SpeakingLogRequest("스피킹 로그1", "https://dummy_record_adsf_1234", "dummy_text_asdf_1234");
                SpeakingLogDetailResponse response = new SpeakingLogDetailResponse( memberId, "스피킹 로그1",
                        "https://dummy_record_adsf_1234","dummy_text_asdf_1234", likeCount, false,  new ArrayList<>());
                BaseResponse<SpeakingLogDetailResponse> baseResponse = new BaseResponse<>(CREATE_SPEAKING_LOG_SUCCESS, response);

                when(speakingLogService.create(refEq(request), refEq(memberId)))
                    .thenReturn(response);

                //when
                ResultActions resultActions = mockMvc.perform(post("/speaking-log")
                    .content(objectMapper.writeValueAsString(request))
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .contentType(MediaType.APPLICATION_JSON_VALUE));

                //then
                resultActions.andExpect(status().isOk())
                    .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
                    .andDo(print());

                verify(speakingLogService).create(refEq(request), refEq(memberId));
            }
        }
    }

}
