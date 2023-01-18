package com.example.be.core.application.dto.request;

public class SpeakingLogModifyRequest {

	private String title;
	private String voiceRecord;
	private String voiceText;

	private SpeakingLogModifyRequest() {}

	public SpeakingLogModifyRequest(String title, String voiceRecord, String voiceText) {
		this.title = title;
		this.voiceRecord = voiceRecord;
		this.voiceText = voiceText;
	}
}
