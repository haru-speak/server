package com.example.be.core.web;

import static com.example.be.common.response.ResponseCodeAndMessages.GENERATE_IMAGE_UPLOAD_URL_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.GENERATE_VOICE_UPLOAD_URL_SUCCESS;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.FileUploadService;
import com.example.be.core.application.dto.response.PreSignedUrlResponse;
import java.io.IOException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/file")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/image")
    public BaseResponse<PreSignedUrlResponse> uploadImage(
        @RequestParam("extension") String extension
    ) throws IOException {
        return new BaseResponse<>(
            GENERATE_IMAGE_UPLOAD_URL_SUCCESS,
            fileUploadService.uploadUrl("image" ,extension)
        );
    }

    @PostMapping("/voice")
    public BaseResponse<PreSignedUrlResponse> uploadVoice(
        @RequestParam("extension") String extension
    ) throws IOException {
        return new BaseResponse<>(
            GENERATE_VOICE_UPLOAD_URL_SUCCESS,
            fileUploadService.uploadUrl("voice" ,extension)
        );
    }
}
