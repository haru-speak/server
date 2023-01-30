package com.example.be.core.web.speakinglog;

import static com.example.be.common.response.ResponseCodeAndMessages.MODIFY_SPEAKING_LOG_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.application.dto.request.SpeakingLogModifyRequest;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import com.example.be.core.web.InitControllerTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class SpeakingLogControllerModifyTest extends InitControllerTest {

    @MockBean
    protected SpeakingLogService speakingLogService;

    @Nested
    @DisplayName("Speaking Log 수정할 때")
    class ModifyTest {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class NormalTest {

            @Test
            @DisplayName("스피킹 로그 제목 수정 시, 해당 ID를 가진 스피킹 로그의 제목이 수정된다.")
            void modify_speaking_log() throws Exception {
                //given
                Long speakingLogId = 1L;
                Long memberId = 3L;
                Integer likeCount = 10;

                SpeakingLogModifyRequest request = new SpeakingLogModifyRequest(
                    "스피킹 로그 2",
                    "https://dummy-record-abcd-4321",
                    "dummy-text-abcd-4321"
                );

                SpeakingLogDetailResponse response = new SpeakingLogDetailResponse(
                    memberId,
                    "스피킹 로그 2",
                    "https://dummy-record-abcd-4321",
                    "dummy-text-abcd-4321",
                    likeCount,
                    Boolean.TRUE,
                    new ArrayList<>()
                );

                BaseResponse<SpeakingLogDetailResponse> baseResponse = new BaseResponse<>(MODIFY_SPEAKING_LOG_SUCCESS, response);

                when(speakingLogService.modify(refEq(speakingLogId), refEq(request)))
                    .thenReturn(response);

                //when
                ResultActions resultActions = mockMvc.perform(
                    put("/speaking-log/{speakingLogId}", speakingLogId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)));

                //then
                resultActions.andExpect(status().isOk())
                    .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));

                verify(speakingLogService).modify(refEq(speakingLogId), refEq(request));
            }
        }
    }
}
