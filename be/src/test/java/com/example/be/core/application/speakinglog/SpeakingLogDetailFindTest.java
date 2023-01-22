package com.example.be.core.application.speakinglog;

import static com.example.be.common.exception.ErrorCodeAndMessages.SPEAKING_LOG_ID_NOT_FOUND_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.speakinglog.NotFoundSpeakingLogIdException;
import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("서비스 테스트 : SpeakingLog 상세 조회")
public class SpeakingLogDetailFindTest extends InitServiceTest {
	@Autowired
	private SpeakingLogService speakingLogService;

	@Nested
	@DisplayName("스피킹 로그를 상세 조회할 때")
	class DetailFindTest {

		@Nested
		@DisplayName("정상적인 요청이라면")
		class NormalTest {

			@Test
			@DisplayName("해당 아이디를 가진 스피킹 로그가 조회된다.")
			void normal_detail_find() {

				//given
				Long speakingLogId = 1L;
				Long memberId = 1L;

				//when
				SpeakingLogDetailResponse response = speakingLogService.findById(speakingLogId, memberId);

				//then
				assertThat(response.getMemberId()).isEqualTo(1L);
				assertThat(response.getIsLiked()).isFalse();
				assertThat(response.getLikeCount()).isZero();
				assertThat(response.getTitle()).isEqualTo("speaking-log-title11");
				assertThat(response.getVoiceRecord()).isEqualTo("dummy-voice-record11");
				assertThat(response.getVoiceText()).isEqualTo("dummy-voice-text11");
			}
		}

		@Nested
		@DisplayName("비정상적인 요청이라면")
		class AbnormalTest {

			@Nested
			@DisplayName("스피킹 로그 아이디가 없는 아이디 일 때")
			class WrongSpeakingLogId {

				@Test
				@DisplayName("예외(NotFoundSpeakingLogIdException)를 던진다.")
				void normal_detail_find() {

					//given
					Long speakingLogId = (long) Integer.MAX_VALUE;
					Long memberId = 1L;

					//when & then
					assertThatThrownBy(() -> speakingLogService.findById(speakingLogId, memberId))
						.isInstanceOf(BaseException.class)
						.isExactlyInstanceOf(NotFoundSpeakingLogIdException.class)
						.hasMessage(SPEAKING_LOG_ID_NOT_FOUND_ERROR.getMessage());
				}

			}
		}
	}
}
