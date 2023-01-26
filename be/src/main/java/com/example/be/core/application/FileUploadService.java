package com.example.be.core.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.be.core.application.dto.response.FileUploadResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
    private final AmazonS3 amazonS3;

    public FileUploadService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public FileUploadResponse upload(String category, MultipartFile multipartFile) throws IOException {

        String fileName = createFileName(category ,multipartFile.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(new PutObjectRequest(bucket, fileName, multipartFile.getInputStream(), objectMetadata)
            .withCannedAcl(CannedAccessControlList.PublicRead));

        return new FileUploadResponse(
            amazonS3.getUrl(bucket, fileName).toString(),
            fileName
        );
    }

    private String createFileName(String category, String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf(".");
        String fileExtension = originalFileName.substring(fileExtensionIndex);
        String fileName = originalFileName.substring(0, fileExtensionIndex);
        String random = String.valueOf(UUID.randomUUID());

        return category + "/" + fileName + "-" + random + fileExtension;
    }

    public void delete(String filePath) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, filePath));
    }
}
