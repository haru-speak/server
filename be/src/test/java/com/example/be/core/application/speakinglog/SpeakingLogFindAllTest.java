package com.example.be.core.application.speakinglog;

import static com.example.be.common.exception.ErrorCodeAndMessages.SPEAKING_LOG_DATE_FORMAT_ERROR;
import static org.assertj.core.api.Assertions.*;

import com.example.be.common.exception.speakinglog.InvalidSpeakingLogDateException;
import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.application.dto.request.SpeakingLogConditionRequest;
import com.example.be.core.application.dto.response.SpeakingLogsResponse;
import com.example.be.core.domain.member.Member;
import com.example.be.core.domain.speakinglog.SpeakingLog;
import com.example.be.core.repository.member.MemberRepository;
import com.example.be.core.repository.speakinglog.SpeakingLogRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("서비스 테스트 : SpeakingLog 전체 조회")
public class SpeakingLogFindAllTest {

    @Autowired
    private SpeakingLogService speakingLogService;

    @Autowired
    private SpeakingLogRepository speakingLogRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void init() {
        Member loginMember = new Member("nathan", "nathan1234@google.com", "asdf1234@", "profileImage");
        memberRepository.save(loginMember);
        SpeakingLog speakingLog1 = new SpeakingLog(
            loginMember,
            "첫 번째 스피킹 로그",
            "dummy-voice-record-data",
            "dummy-voice-text-data"
        );
        SpeakingLog speakingLog2 = new SpeakingLog(
            loginMember,
            "두 번째 스피킹 로그",
            "dummy-voice-record-data",
            "dummy-voice-text-data"
        );
        speakingLogRepository.save(speakingLog1);
        speakingLogRepository.save(speakingLog2);
    }

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
                assertThat(response.getSpeakingLogResponses().size()).isEqualTo(2);
            }
        }
    }
}
