package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class QuestionResponse {

    @Schema(type = "Long", description = "오늘의 문장 ID, NOT NULL")
    private final Long questionId;

    @Schema(type = "String", description = "문장 제목, NOT NULL")
    private final String title;

    @Schema(type = "String", description = "음성 녹음 URL, NOT NULL")
    private final String voidRecord;

    @Schema(type = "String", description = "음성 텍스트 URL, NOT NULL")
    private final String voiceText;

    public QuestionResponse(Long questionId, String title, String voidRecord, String voiceText) {
        this.questionId = questionId;
        this.title = title;
        this.voidRecord = voidRecord;
        this.voiceText = voiceText;
    }
}
