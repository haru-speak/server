package com.example.be.core.application.speakinglog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.speakinglog.NotFoundMemberIdException;
import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.dto.request.SpeakingLogRequest;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("서비스 테스트 : SpeakingLog 생성")
class SpeakingLogCreateTest extends InitServiceTest {

	@Nested
	@DisplayName("새로운 스피킹 로그를 생성할 때")
	class CreateTest {

		@Nested
		@DisplayName("정상적인 요청이라면")
		class NormalTest {

			@Test
			@DisplayName("로그인 한 멤버의 스피킹 로그가 생성된다.")
			void normal_create() {

				//given
				Long memberId = 1L;
				SpeakingLogRequest request = new SpeakingLogRequest("동동의 스피킹 로그",
					"dummy-voice-record-data", "dummy-voice-text-data");

				//when
				SpeakingLogDetailResponse response = speakingLogService.create(request, memberId);

				//then
				assertThat(response.getMemberId()).isEqualTo(1L);
				assertThat(response.getIsLiked()).isFalse();
				assertThat(response.getLikeCount()).isZero();
				assertThat(response.getTitle()).isEqualTo("동동의 스피킹 로그");
				assertThat(response.getVoiceRecord()).isEqualTo("dummy-voice-record-data");
				assertThat(response.getVoiceText()).isEqualTo("dummy-voice-text-data");
			}
		}

		@Nested
		@DisplayName("비정상적인 요청이라면")
		class AbnormalTest {

			@Test
			@DisplayName("로그인 멤버 ID가 잘못된 경우 예외(NotFoundMemberIdException)를 던진다.")
			void abnormal_login_member_id_is_wrong() {

				//given
				Long memberId = (long) Integer.MAX_VALUE;
				SpeakingLogRequest request = new SpeakingLogRequest("동동의 스피킹 로그",
					"dummy-voice-record-data", "dummy-voice-text-data");

				//when & then
				assertThatThrownBy(() -> speakingLogService.create(request, memberId))
					.isInstanceOf(BaseException.class)
					.isExactlyInstanceOf(NotFoundMemberIdException.class);
			}
		}
	}
}
