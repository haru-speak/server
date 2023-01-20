package com.example.be.core.application.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SpeakingLogRequest {

    @Schema(type = "String", description = "제목, NOT NULL")
    private String title;

    @Schema(type = "String", description = "음성 녹음 URL, NOT NULL")
    private String voiceRecord;

    @Schema(type = "String", description = "음성 텍스트 URL, NOT NULL")
    private String voiceText;

    private SpeakingLogRequest() {}

    public SpeakingLogRequest(String title, String voiceRecord, String voiceText) {
        this.title = title;
        this.voiceRecord = voiceRecord;
        this.voiceText = voiceText;
    }

    @Override
    public String toString() {
        return "title: " + title + ", voiceRecord: " + voiceRecord
            + ", voiceText: " + voiceText;
    }
}
