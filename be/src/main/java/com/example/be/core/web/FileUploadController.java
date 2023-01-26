package com.example.be.core.web;

import static com.example.be.common.response.ResponseCodeAndMessages.IMAGE_UPLOAD_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.VOICE_UPLOAD_SUCCESS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.FileUploadService;
import com.example.be.core.application.dto.response.FileUploadResponse;
import java.io.IOException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/upload", produces = APPLICATION_JSON_VALUE)
public class FileUploadController {

    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/image")
    public BaseResponse<FileUploadResponse> uploadImage(
        @RequestPart("file") MultipartFile multipartFile) throws IOException {
        return new BaseResponse<>(IMAGE_UPLOAD_SUCCESS, fileUploadService.upload("image",multipartFile));
    }

    @PostMapping("/voice")
    public BaseResponse<FileUploadResponse> uploadVoice(
        @RequestPart("file") MultipartFile multipartFile) throws IOException {
        return new BaseResponse<>(VOICE_UPLOAD_SUCCESS, fileUploadService.upload("voice", multipartFile));
    }
}
