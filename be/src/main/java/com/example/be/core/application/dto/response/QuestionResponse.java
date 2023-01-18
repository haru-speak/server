package com.example.be.core.application.dto.response;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class QuestionResponse {

    @NotBlank
    private final String title;

    @NotBlank
    private final String voidRecord;

    @NotBlank
    private final String voiceText;

    public QuestionResponse(String title, String voidRecord, String voiceText) {
        this.title = title;
        this.voidRecord = voidRecord;
        this.voiceText = voiceText;
    }
}
