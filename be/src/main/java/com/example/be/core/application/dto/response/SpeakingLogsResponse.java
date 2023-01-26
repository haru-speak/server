package com.example.be.core.application.dto.response;

import com.example.be.core.domain.speakinglog.SpeakingLogType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpeakingLogsResponse {

	@Schema(enumAsRef = true, description = "스피킹 로그 조회 타입")
	private SpeakingLogType type;

	@Schema(type = "date", description = "스피킹 로그 조회 날짜")
	private LocalDate date;

	private List<SpeakingLogResponse> speakingLogsResponse;

	public SpeakingLogsResponse(SpeakingLogType type, LocalDate date,
		List<SpeakingLogResponse> speakingLogsResponse) {
		this.type = type;
		this.date = date;
		this.speakingLogsResponse = speakingLogsResponse;
	}
}
