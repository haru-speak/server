package com.example.be.core.web;

import static com.example.be.common.response.ResponseCodeAndMessages.FIND_SPEAKING_LOG_SUCCESS;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.application.dto.response.SpeakingLogsResponse;
import com.example.be.core.domain.SpeakingLogType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@WebMvcTest(SpeakingLogController.class)
class SpeakingLogControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SpeakingLogService speakingLogService;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void init(WebApplicationContext wc) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wc)
			.addFilter(new CharacterEncodingFilter("UTF-8", true))
			.build();
	}
	@Nested
	@DisplayName("Speaking Log를 조회할 때")
	class RetrieveTest {

		@Nested
		@DisplayName("정상적인 요청이라면")
		class NormalTest {


			@Nested
			@DisplayName("날짜 데이터가 들어갔을 때")
			class WithDateTest {

				@Test
				@DisplayName("ALL TYPE 스피킹 로그 조회시 해당 날짜의 ALL TYPE 스피킹 로그가 조회된다")
				void find_all_type_speaking_log_with_date() throws Exception {
					//given
					SpeakingLogsResponse speakingLogsResponse = new SpeakingLogsResponse(SpeakingLogType.ALL, null);
					BaseResponse<SpeakingLogsResponse> baseResponse = new BaseResponse<>(FIND_SPEAKING_LOG_SUCCESS, speakingLogsResponse);

					when(speakingLogService.find(SpeakingLogType.ALL, LocalDate.of(2023, 1, 18)))
						.thenReturn(speakingLogsResponse);

					//when
					ResultActions resultActions = mockMvc.perform(get("/api/speaking-log?type=all&date=20230118")
						.accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE));

					//then
					resultActions.andExpect(status().isOk())
						.andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));

					// enum : Singleton, LocalDate.of() : equals() & hashCode()  overriding
					verify(speakingLogService).find(SpeakingLogType.ALL, LocalDate.of(2023, 1, 18));
				}

				@Test
				@DisplayName("타입 없이 스피킹 로그 조회시 해당 날짜의 MY TYPE 스피킹 로그가 조회된다.")
				void find_no_type_speaking_log_with_date() throws Exception {
					//given
					SpeakingLogsResponse speakingLogsResponse = new SpeakingLogsResponse(SpeakingLogType.MY, null);
					BaseResponse<SpeakingLogsResponse> baseResponse = new BaseResponse<>(FIND_SPEAKING_LOG_SUCCESS, speakingLogsResponse);

					when(speakingLogService.find(SpeakingLogType.MY, LocalDate.of(2023, 1, 18)))
						.thenReturn(speakingLogsResponse);

					//when
					ResultActions resultActions = mockMvc.perform(get("/api/speaking-log?date=20230118")
						.accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE));

					//then
					resultActions.andExpect(status().isOk())
						.andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));

					// enum : Singleton, LocalDate.of() : equals() & hashCode()  overriding
					verify(speakingLogService).find(SpeakingLogType.MY, LocalDate.of(2023, 1, 18));
				}
			}

			@Nested
			@DisplayName("날짜 데이터가 들어가지 않았을 때")
			class NoDateTest {

				@Test
				@DisplayName("ALL TYPE 스피킹 로그 조회시 오늘 날짜의 ALL TYPE 스피킹 로그가 조회된다")
				void find_all_type_speaking_log_with_no_date() throws Exception {
					//given
					SpeakingLogsResponse speakingLogsResponse = new SpeakingLogsResponse(SpeakingLogType.ALL, null);
					BaseResponse<SpeakingLogsResponse> baseResponse = new BaseResponse<>(FIND_SPEAKING_LOG_SUCCESS, speakingLogsResponse);

					when(speakingLogService.find(SpeakingLogType.ALL, LocalDate.now()))
						.thenReturn(speakingLogsResponse);

					//when
					ResultActions resultActions = mockMvc.perform(get("/api/speaking-log?type=all")
						.accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE));

					//then
					resultActions.andExpect(status().isOk())
						.andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));

					// enum : Singleton, LocalDate.of() : equals() & hashCode()  overriding
					verify(speakingLogService).find(SpeakingLogType.ALL, LocalDate.now());
				}

				@Test
				@DisplayName("타입 없이 스피킹 로그 조회시 오늘 날짜의 MY TYPE 스피킹 로그가 조회된다")
				void find_no_type_speaking_log_with_no_date() throws Exception {
					//given
					SpeakingLogsResponse speakingLogsResponse = new SpeakingLogsResponse(SpeakingLogType.MY, null);
					BaseResponse<SpeakingLogsResponse> baseResponse = new BaseResponse<>(FIND_SPEAKING_LOG_SUCCESS, speakingLogsResponse);

					when(speakingLogService.find(SpeakingLogType.MY, LocalDate.now()))
						.thenReturn(speakingLogsResponse);

					//when
					ResultActions resultActions = mockMvc.perform(get("/api/speaking-log")
						.accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE));

					//then
					resultActions.andExpect(status().isOk())
						.andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));

					// enum : Singleton, LocalDate.of() : equals() & hashCode()  overriding
					verify(speakingLogService).find(SpeakingLogType.MY, LocalDate.now());
				}
			}
		}
	}
}
