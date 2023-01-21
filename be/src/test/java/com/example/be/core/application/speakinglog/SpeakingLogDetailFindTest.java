package com.example.be.core.application.speakinglog;

import static org.assertj.core.api.Assertions.assertThat;

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
	}
}
