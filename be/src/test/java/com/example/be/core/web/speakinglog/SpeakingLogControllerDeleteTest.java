package com.example.be.core.web.speakinglog;


import static com.example.be.common.response.ResponseCodeAndMessages.DELETE_SPEAKING_LOG_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.web.InitControllerTest;
import com.example.be.core.web.SpeakingLogController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(SpeakingLogController.class)

public class SpeakingLogControllerDeleteTest extends InitControllerTest {

    @MockBean
    protected SpeakingLogService speakingLogService;

    @Nested
    @DisplayName("Speaking Log 삭제할 때")
    class DeleteTest {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class NormalTest {

            @Test
            @DisplayName("스피킹 로그 삭제시 해당 ID를 가진 스피킹 로그가 삭제된다")
            void delete_speaking_log() throws Exception {
                //given
                Long speakingLogId = 1L;
                BaseResponse<Void> baseResponse = new BaseResponse<>(DELETE_SPEAKING_LOG_SUCCESS, null);

                //when
                ResultActions resultActions = mockMvc.perform(delete("/speaking-log/{speakingLogId}", speakingLogId)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE));

                //then
                resultActions.andExpect(status().isOk())
                        .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));

                verify(speakingLogService).delete(refEq(speakingLogId));
            }
        }
    }
}
