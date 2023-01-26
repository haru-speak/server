package com.example.be.core.application.dto.response;

import com.example.be.core.domain.speakinglog.SpeakingLogType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;

@Getter
public class SpeakingLogsResponse {

	@Schema(enumAsRef = true, description = "스피킹 로그 조회 타입")
	private final SpeakingLogType type;

	@Schema(type = "date", description = "스피킹 로그 조회 날짜")
	private final LocalDate date;

	private final List<SpeakingLogResponse> speakingLogsResponse;

	public SpeakingLogsResponse(SpeakingLogType type, LocalDate date,
		List<SpeakingLogResponse> speakingLogsResponse) {
		this.type = type;
		this.date = date;
		this.speakingLogsResponse = speakingLogsResponse;
	}
}
