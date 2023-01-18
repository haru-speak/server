package com.example.be.core.application.dto.response;

import lombok.Getter;

@Getter
public class QuestionResponse {

    private String title;
    private String voidRecord;
    private String voiceText;

    private QuestionResponse() {}

    public QuestionResponse(String title, String voidRecord, String voiceText) {
        this.title = title;
        this.voidRecord = voidRecord;
        this.voiceText = voiceText;
    }
}
