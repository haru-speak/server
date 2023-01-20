package com.example.be.core.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class SpeakingLogModifyRequest {

	@Schema(type = "String", description = "변경할 제목, NOT NULL")
	private String title;

	@Schema(type = "String", description = "변경할 음성 녹음 URL, NOT NULL")
	private String voiceRecord;

	@Schema(type = "String", description = "변경할 음성 텍스트 URL, NOT NULL")
	private String voiceText;

	private SpeakingLogModifyRequest() {}

	public SpeakingLogModifyRequest(String title, String voiceRecord, String voiceText) {
		this.title = title;
		this.voiceRecord = voiceRecord;
		this.voiceText = voiceText;
	}
}
