package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class PreSignedUrlResponse {

    @Schema(type = "String", description = "파일 접근 URL, NOT NULL")
    private final String preSignedUrl;

    public PreSignedUrlResponse(String preSignedUrl) {
        this.preSignedUrl = preSignedUrl;
    }
}
