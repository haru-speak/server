package com.example.be.core.application.speakinglog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.speakinglog.NotFoundSpeakingLogIdException;
import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.application.dto.request.SpeakingLogModifyRequest;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import com.example.be.core.domain.speakinglog.SpeakingLog;
import com.example.be.core.repository.speakinglog.SpeakingLogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@DisplayName("서비스 테스트 : SpeakingLog 전체 조회")
public class SpeakingLogModifyTest extends InitServiceTest {

    @Autowired
    SpeakingLogService speakingLogService;

    @Autowired
    SpeakingLogRepository speakingLogRepository;

    @Nested
    @DisplayName("스피킹 로그를 수정할 때")
    class modify_test {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class normal_test {

            @Test
            @DisplayName("스피킹 로그의 제목, 음성파일 URL, 음성파일 텍스트가 변경된다.")
            void normal_modify() throws Exception {
                //given
                Long speakingLogId = 1L;
                SpeakingLogModifyRequest speakingLogModifyRequest = new SpeakingLogModifyRequest("동동동동", "동동동동", "동동동동");

                //when
                SpeakingLogDetailResponse result = speakingLogService.modify(speakingLogId,
                    speakingLogModifyRequest);

                //then
                SpeakingLog after = speakingLogRepository.findById(1L).get();
                assertThat(after.getTitle()).isEqualTo(result.getTitle());
                assertThat(after.getVoiceRecord()).isEqualTo(result.getVoiceRecord());
                assertThat(after.getVoiceText()).isEqualTo(result.getVoiceText());
            }
        }

        @Nested
        @DisplayName("비정상적인 요청이라면")
        class abnormal_test {

            @Test
            @DisplayName("스피킹 로그 ID를 찾을 수 없을 때 NotFoundSpeakingLogIdException 예외를 보낸다.")
            void abnormal_modify() throws Exception {
                //given
                Long speakingLogId = 987654321L;
                SpeakingLogModifyRequest speakingLogModifyRequest = new SpeakingLogModifyRequest("동동동동", "동동동동", "동동동동");

                //when & then
                assertThatThrownBy(() -> speakingLogService.modify(speakingLogId, speakingLogModifyRequest))
                    .isInstanceOf(BaseException.class)
                    .isExactlyInstanceOf(NotFoundSpeakingLogIdException.class);
            }
        }

    }
}
