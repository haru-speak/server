package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class PreSignedUrlResponse {

    @Schema(type = "String", description = "파일 접근 URL, NOT NULL")
    private final String preSignedUrl;

    @Schema(type = "String", description = "실제로 저장되는 파일 이름, NOT NULL")
    private final String fileName;

    public PreSignedUrlResponse(String preSignedUrl, String fileName) {
        this.preSignedUrl = preSignedUrl;
        this.fileName = fileName;
    }
}