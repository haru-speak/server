package com.example.be.core.application.dto.response;

import com.example.be.core.domain.SpeakingLogType;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SpeakingLogsResponse {

	@NotNull
	private final SpeakingLogType type;
	private final List<SpeakingLogResponse> speakingLogResponses;
	public SpeakingLogsResponse(SpeakingLogType type,
		List<SpeakingLogResponse> speakingLogResponses) {
		this.type = type;
		this.speakingLogResponses = speakingLogResponses;
	}
}
