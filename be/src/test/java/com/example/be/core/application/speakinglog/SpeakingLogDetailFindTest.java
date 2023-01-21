package com.example.be.core.application.speakinglog;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import com.example.be.core.domain.member.Member;
import com.example.be.core.domain.speakinglog.SpeakingLog;
import com.example.be.core.repository.member.MemberRespository;
import com.example.be.core.repository.speakinglog.SpeakingLogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@DisplayName("서비스 테스트 : SpeakingLog 상세 조회")
public class SpeakingLogDetailFindTest {

	@Autowired
	private SpeakingLogService speakingLogService;

	@Autowired
	private SpeakingLogRepository speakingLogRepository;

	@Autowired
	private MemberRespository memberRespository;

	@BeforeEach
	void init() {
		Member loginMember = new Member("nathan");
		memberRespository.save(loginMember);
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
				SpeakingLogDetailResponse response = speakingLogService.findById(speakingLogId, memberId);

				//then
				assertThat(response.getMemberId()).isEqualTo(1L);
				assertThat(response.getIsLiked()).isFalse();
				assertThat(response.getLikeCount()).isZero();
				assertThat(response.getTitle()).isEqualTo("첫 번째 스피킹 로그");
				assertThat(response.getVoiceRecord()).isEqualTo("dummy-voice-record-data");
				assertThat(response.getVoiceText()).isEqualTo("dummy-voice-text-data");
			}
		}
	}
}
