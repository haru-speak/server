package com.example.be.core.web.speakinglog;

import static com.example.be.common.response.ResponseCodeAndMessages.FIND_DETAIL_SPEAKING_LOG_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import com.example.be.core.web.InitControllerTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class SpeakingLogControllerDetailFindTest extends InitControllerTest {

	@MockBean
	protected SpeakingLogService speakingLogService;

	@Nested
	@DisplayName("Speaking Log 상세 조회할 때")
	class DetailFindTest {

		@Nested
		@DisplayName("정상적인 요청이라면")
		class NormalTest {

			@Test
			@DisplayName("스피킹 로그 상세 조회시, 해당 ID를 가진 스피킹 로그가 조회된다.")
			void find_detail_speaking_log() throws Exception {
				//given
				Long speakingLogId = 1L;
				Long memberId = 1L;
				Integer likeCount = 10;
				SpeakingLogDetailResponse response = new SpeakingLogDetailResponse(memberId, "스피킹 로그 1",
					"dummy-record-abcd-1234", "dummy-text-abcd-1234", likeCount, Boolean.TRUE, new ArrayList<>());
				BaseResponse<SpeakingLogDetailResponse> baseResponse = new BaseResponse<>(FIND_DETAIL_SPEAKING_LOG_SUCCESS, response);

				when(speakingLogService.findById(refEq(speakingLogId), refEq(memberId)))
					.thenReturn(response);

				//when
				ResultActions resultActions = mockMvc.perform(get("/speaking-log/{speakingLogId}", speakingLogId)
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON_VALUE));

				//then
				resultActions.andExpect(status().isOk())
					.andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));

				verify(speakingLogService).findById(refEq(speakingLogId), refEq(memberId));
			}
		}
	}

}
