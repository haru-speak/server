package com.example.be.core.application.speakinglog;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.domain.speakinglog.SpeakingLog;
import com.example.be.core.repository.speakinglog.SpeakingLogRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("서비스 테스트 : SpeakingLog 삭제")
public class SpeakingLogDeleteTest extends InitServiceTest {

    @Autowired
    private SpeakingLogService speakingLogService;

    @Autowired
    private SpeakingLogRepository speakingLogRepository;

    @Nested
    @DisplayName("스피킹 로그를 삭제할 때")
    class deleteTest {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class NormalTest {

            @Test
            @DisplayName("삭제 전과 삭제 후의 스피킹 로그 수 차이가 발생한다.")
            void normal_delete() throws Exception {

                //given
                Long speakingLogId = 1L;
                List<SpeakingLog> before = speakingLogRepository.findAll();
                assertThat(before.size()).isEqualTo(15);

                //when
                speakingLogService.delete(speakingLogId);

                //then
                List<SpeakingLog> after = speakingLogRepository.findAll();
                assertThat(after).size().isEqualTo(14);
            }
        }
    }
}
