package com.example.be.core.web;

import static com.example.be.common.response.ResponseCodeAndMessages.CREATE_SPEAKING_LOG_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.DELETE_SPEAKING_LOG_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.FIND_SPEAKING_LOG_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.MODIFY_SPEAKING_LOG_SUCCESS;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.application.dto.request.SpeakingLogRequest;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import com.example.be.core.application.dto.response.SpeakingLogsResponse;
import com.example.be.core.domain.SpeakingLogType;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		@RequestParam(name = "date", required = false, defaultValue = "#{T(java.time.LocalDateTime).now()}")
		@DateTimeFormat(pattern = "yyyyMMdd") LocalDate date) {

		SpeakingLogsResponse response = speakingLogService.find(type, date);
		return new BaseResponse<>(FIND_SPEAKING_LOG_SUCCESS, response);
	}

	@GetMapping("/{speakingLogId}")
	@ApiOperation(value = "스피킹 로그 상세 조회입니다.")
	public BaseResponse<SpeakingLogDetailResponse> findById(@PathVariable final Long speakingLogId) {
		SpeakingLogDetailResponse response = speakingLogService.findById(speakingLogId);
		return new BaseResponse<>(FIND_SPEAKING_LOG_SUCCESS, response);
	}

	@DeleteMapping("/{speakingLogId}")
	@ApiOperation(value = "스피킹 로그 삭제입니다.")
	public BaseResponse<Void> delete(@PathVariable final Long speakingLogId) {
		speakingLogService.delete(speakingLogId);
		return new BaseResponse<>(DELETE_SPEAKING_LOG_SUCCESS, null);
	}

	@PutMapping("/{speakingLogId}")
	@ApiOperation(value = "스피킹 로그 수정입니다.")
	public BaseResponse<SpeakingLogDetailResponse> modify(
		@PathVariable final Long speakingLogId,
		@RequestBody final SpeakingLogRequest speakingLogRequest) {
		speakingLogService.modify(speakingLogId, speakingLogRequest);
		return new BaseResponse<>(MODIFY_SPEAKING_LOG_SUCCESS, null);
	}

	@PostMapping
	@ApiOperation(value = "스피킹 로그 생성입니다.")
	public BaseResponse<SpeakingLogDetailResponse> create(
		@RequestBody final SpeakingLogRequest speakingLogRequest) {

		SpeakingLogDetailResponse response = speakingLogService.create(speakingLogRequest);
		return new BaseResponse<>(CREATE_SPEAKING_LOG_SUCCESS, response);
	}
}
