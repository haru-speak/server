package com.example.be.core.application.dto.request;


import lombok.Getter;

@Getter
public class SpeakingLogRequest {

    private String title;
    private String voiceRecord;
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
