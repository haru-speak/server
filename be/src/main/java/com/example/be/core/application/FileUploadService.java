package com.example.be.core.application;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.example.be.core.application.dto.response.PreSignedUrlResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
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

    public String generateUrl(String fileName, HttpMethod httpMethod) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);

        return amazonS3.generatePresignedUrl(bucket, fileName, calendar.getTime(), httpMethod).toString();
    }

    @Async
    public PreSignedUrlResponse uploadUrl(String category, String extension) {
        String fileName = category + "/" + UUID.randomUUID() + extension;
        return new PreSignedUrlResponse(generateUrl(fileName, HttpMethod.PUT));
    }
}
