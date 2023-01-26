package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FileUploadResponse {

    @Schema(type = "String", description = "파일 접근 URL, NOT NULL")
    @NotBlank
    private final String fileUrl;

    @Schema(type = "String", description = "파일 삭제 시 필요한 Path, NOT NULL")
    @NotBlank
    private final String filePath;

    public FileUploadResponse(String fileUrl, String filePath) {
        this.fileUrl = fileUrl;
        this.filePath = filePath;
    }
}
