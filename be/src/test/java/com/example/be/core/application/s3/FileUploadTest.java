package com.example.be.core.application.s3;

import com.example.be.core.application.FileUploadService;
import com.example.be.core.tool.AwsS3MockConfigurator;
import io.findify.s3mock.S3Mock;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
@Import(AwsS3MockConfigurator.class)
@DisplayName("서비스 테스트 : File(이미지, 음성파일) 업로드")
public class FileUploadTest {

    @Autowired
    private S3Mock s3Mock;

    @Autowired
    private FileUploadService fileUploadService;

    @AfterEach
    public void s3MockStop() {
        s3Mock.stop();
    }

    @Nested
    @DisplayName("파일을 업로드 할 때")
    class uploadTest {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class  normal_test {

            @Test
            @DisplayName("S3에 이미지가 업로드 된다.")
            void image_upload() throws IOException {
                //given
                String path = "test.png";
                String contentType = "image/png";
                String dirName = "image";

                MockMultipartFile file =
                    new MockMultipartFile(
                        "test",
                        path,
                        contentType,
                        "test".getBytes()
                    );

                //when
                //FileUploadResponse response = fileUploadService.upload(dirName, file);

                //then
                //assertThat(response.getFilePath()).contains(path);
                //assertThat(response.getFilePath()).contains(dirName);
            }

            @Test
            @DisplayName("S3에 음성 파일이 업로드 된다.")
            void voice_upload() throws IOException {
                //given
                String path = "test.mid";
                String contentType = "audio/midi";
                String dirName = "voice";

                MockMultipartFile file =
                    new MockMultipartFile(
                        "test",
                        path,
                        contentType,
                        "test".getBytes()
                    );

                //when
                //FileUploadResponse response = fileUploadService.upload(dirName, file);

                //then
                //assertThat(response.getFilePath()).contains(path);
                //assertThat(response.getFilePath()).contains(dirName);
            }
        }
    }
}
