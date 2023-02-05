package com.example.be.core.application.speakinglog;

import static com.example.be.common.exception.ErrorCodeAndMessages.SPEAKING_LOG_ID_NOT_FOUND_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.speakinglog.NotFoundSpeakingLogIdException;
import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import com.example.be.core.domain.member.Member;
import com.example.be.core.domain.speakinglog.SpeakingLog;
import com.example.be.core.repository.member.MemberRepository;
import com.example.be.core.repository.speakinglog.SpeakingLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("서비스 테스트 : SpeakingLog 상세 조회")
class SpeakingLogDetailFindTest extends InitServiceTest {
	@Autowired
	private SpeakingLogService speakingLogService;

	@Autowired
	private SpeakingLogRepository speakingLogRepository;

	@Autowired
	private MemberRepository memberRepository;

	@BeforeEach
	void init() {
		Member loginMember = new Member("nathan", "nathan1234@google.com", "asdf1234@", "aaaaa");
		memberRepository.save(loginMember);
		SpeakingLog speakingLog = new SpeakingLog(
			loginMember,
			"첫 번째 스피킹 로그",
			"dummy-voice-record-data",
			"dummy-voice-text-data"
		);
		speakingLogRepository.save(speakingLog);
	}

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
				SpeakingLogDetailResponse response = speakingLogService.findById(memberId,
					speakingLogId);

				//then
				assertThat(response.getMemberId()).isEqualTo(1L);
				assertThat(response.getIsLiked()).isFalse();
				assertThat(response.getLikeCount()).isZero();
				assertThat(response.getTitle()).isEqualTo("speaking-log-title11");
				assertThat(response.getVoiceRecord()).isEqualTo("https://dummy-voice-record11");
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
					assertThatThrownBy(() -> speakingLogService.findById(memberId, speakingLogId))
						.isInstanceOf(BaseException.class)
						.isExactlyInstanceOf(NotFoundSpeakingLogIdException.class)
						.hasMessage(SPEAKING_LOG_ID_NOT_FOUND_ERROR.getMessage());
				}

			}
		}
	}
}
