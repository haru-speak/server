package com.example.be.core.application;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.example.be.core.application.dto.response.PreSignedUrlResponse;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FileUploadService {
    private final AmazonS3 amazonS3;

    public FileUploadService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private String generateUrl(String fileName, HttpMethod httpMethod) {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 10;
        expiration.setTime(expTimeMillis);
        log.debug("[S3 Pre-signed URL 만료 시간] = {}", expiration);
        return amazonS3.generatePresignedUrl(bucket, fileName, expiration, httpMethod).toString();
    }

    public PreSignedUrlResponse uploadUrl(String category, String extension) {
        String fileName = category + "/" + UUID.randomUUID() + extension;
        log.debug("[S3에 업로드 되는 파일 이름] = {}", fileName);
        return new PreSignedUrlResponse(generateUrl(fileName, HttpMethod.PUT), fileName);
    }
}