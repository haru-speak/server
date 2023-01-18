package com.example.be.core.web;

import static com.example.be.common.response.ResponseCodeAndMessages.FIND_SPEAKING_LOG_SUCCESS;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import com.example.be.core.application.dto.response.SpeakingLogsResponse;
import com.example.be.core.domain.SpeakingLogType;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/speaking-log")
public class SpeakingLogController {

	private final SpeakingLogService speakingLogService;

	public SpeakingLogController(SpeakingLogService speakingLogService) {
		this.speakingLogService = speakingLogService;
	}

	@GetMapping
	@ApiOperation(value = "스피킹 로그 타입에 따른 전체 조회입니다.")
	public BaseResponse<SpeakingLogsResponse> find(
		@RequestParam(name = "type", required = false, defaultValue = "MY") SpeakingLogType type,
		@RequestParam(name = "date", required = false, defaultValue = "#{T(java.time.LocalDateTime).now()}") LocalDate date) {

		SpeakingLogsResponse response = speakingLogService.find(type, date);
		return new BaseResponse<>(FIND_SPEAKING_LOG_SUCCESS, response);
	}

	@GetMapping("/{speakingLogId}")
	@ApiOperation(value = "스피킹 로그 상세 조회입니다.")
	public BaseResponse<SpeakingLogDetailResponse> findById(@PathVariable final Long speakingLogId) {
		SpeakingLogDetailResponse response = speakingLogService.findById(speakingLogId);
		return new BaseResponse<>(FIND_SPEAKING_LOG_SUCCESS, response);
	}

}
