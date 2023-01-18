package com.example.be.core.application.dto.response;

import com.example.be.core.domain.SpeakingLogType;
import java.util.List;
import lombok.Getter;

@Getter
public class SpeakingLogsResponse {

	private SpeakingLogType type;
	private List<SpeakingLogResponse> speakingLogResponses;

	private SpeakingLogsResponse() {}
	public SpeakingLogsResponse(SpeakingLogType type,
		List<SpeakingLogResponse> speakingLogResponses) {
		this.type = type;
		this.speakingLogResponses = speakingLogResponses;
	}
}
