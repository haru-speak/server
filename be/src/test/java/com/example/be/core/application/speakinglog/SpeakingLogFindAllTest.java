package com.example.be.core.application.speakinglog;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.application.dto.request.SpeakingLogConditionRequest;
import com.example.be.core.application.dto.response.SpeakingLogsResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@DisplayName("서비스 테스트 : SpeakingLog 전체 조회")
public class SpeakingLogFindAllTest extends InitServiceTest {

    @Autowired
    private SpeakingLogService speakingLogService;

    @Nested
    @DisplayName("스피킹 로그를 전체 조회할 때")
    class FindAllTest {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class NormalTest {

            @Test
            @DisplayName("전체 스피킹 로그가 조회된다.")
            void normal_find_all() {
                //given
                String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                //when
                SpeakingLogsResponse response = speakingLogService.find(new SpeakingLogConditionRequest("ALL", today));
                //then
                assertThat(response.getSpeakingLogsResponse().size()).isEqualTo(15);
            }
        }
    }
}
