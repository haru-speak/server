package com.example.be.core.application.s3;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.FileUploadService;
import com.example.be.core.application.dto.response.PreSignedUrlResponse;
import com.example.be.tool.AwsS3MockConfigurator;
import io.findify.s3mock.S3Mock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(AwsS3MockConfigurator.class)
@DisplayName("서비스 테스트 : 업로드를 위한 Pre Signed Url 생성")
public class PreSignedUrlTest {

    @Autowired
    private S3Mock s3Mock;

    @Autowired
    private FileUploadService fileUploadService;

    @AfterEach
    public void s3MockStop() {
        s3Mock.stop();
    }

    @Nested
    @DisplayName("업로드 Pre-signed URL 요청을 할 때")
    class uploadTest {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class normal_test {

            @Test
            @DisplayName("S3에 업로드 할 수 있는 URL 과 실제 업로드 되는 파일 이름을 제공한다.")
            void image_upload_url() throws Exception {
                //given
                String extension = ".jpeg";

                //when
                PreSignedUrlResponse preSignedUrlResponse = fileUploadService.uploadUrl("image", extension);

                //then
                assertThat(preSignedUrlResponse.getPreSignedUrl()).contains("haru-speak-s3");
                assertThat(preSignedUrlResponse.getFileName()).contains(".jpeg");
            }
        }
    }
}
