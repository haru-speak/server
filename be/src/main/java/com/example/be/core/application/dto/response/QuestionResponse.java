package com.example.be.core.application.dto.response;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class QuestionResponse {

    @NotBlank
    private String title;

    @NotBlank
    private String voidRecord;

    @NotBlank
    private String voiceText;

    private QuestionResponse() {}

    public QuestionResponse(String title, String voidRecord, String voiceText) {
        this.title = title;
        this.voidRecord = voidRecord;
        this.voiceText = voiceText;
    }
}
