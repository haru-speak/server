package com.example.be.oauth;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.domain.member.Member;
import com.example.be.core.repository.member.MemberRepository;
import com.example.be.oauth.provider.JwtProvider;
import com.example.be.oauth.provider.LoginService;
import com.example.be.oauth.provider.dto.LoginResponse;
import com.example.be.tool.OAuthMocks;
import com.github.tomakehurst.wiremock.client.WireMock;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = {
	"oauth.kakao.token-url=http://localhost:${wiremock.server.port}/oauth/token",
	"oauth.kakao.resource-url=http://localhost:${wiremock.server.port}/v2/user/me"
})
@DisplayName("OAuth Login 통합 테스트")
@SpringBootTest
@ActiveProfiles("test")
public class OAuthLoginIntegrationTest {

	@Autowired
	private LoginService loginService;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private MemberRepository memberRepository;

	@BeforeEach
	void setUp() throws IOException {
		memberRepository.deleteAllInBatch();
		OAuthMocks.setUpResponses();
	}

	@Test
	@DisplayName("신규 회원이 로그인을 시도했을 때, 로그인 성공 및 멤버 생성 성공")
	void oauth_login_success_with_newbie(){
	    //given
		WireMock.setScenarioState("Kakao Login Success", "Started");

		//when
		LoginResponse response = loginService.login("code");
		String accessToken = response.getAccessToken();
		String refreshToken = response.getRefreshToken();
		Long memberId = response.getMemberId();

		//then
		assertThat(response.getNickname()).isEqualTo("홍길동");
		assertThat(jwtProvider.getAudience(accessToken)).isEqualTo(memberId.toString());
		assertThat(jwtProvider.getAudience(refreshToken)).isEqualTo(memberId.toString());
		assertThat(response.getProfileImage()).isEqualTo("http://yyy.kakao.com/dn/img_640x640.jpg");
		assertThat(response.isNewbie()).isTrue();
	}

	@Test
	@DisplayName("기존 회원이 로그인을 시도했을 때, 로그인 성공 및 멤버 생성 하지 않음")
	void oauth_login_success_with_not_newbie() {
		//given
		WireMock.setScenarioState("Kakao Login Success", "Started");
		memberRepository.save(new Member(
			"홍길동",
			"sample@sample.com",
			"123456789",
			"http://yyy.kakao.com/dn/img_640x640.jpg"
		));

		//when
		LoginResponse response = loginService.login("code");
		String accessToken = response.getAccessToken();
		String refreshToken = response.getRefreshToken();
		Long memberId = response.getMemberId();

		//then
		assertThat(response.getNickname()).isEqualTo("홍길동");
		assertThat(jwtProvider.getAudience(accessToken)).isEqualTo(memberId.toString());
		assertThat(jwtProvider.getAudience(refreshToken)).isEqualTo(memberId.toString());
		assertThat(response.getProfileImage()).isEqualTo("http://yyy.kakao.com/dn/img_640x640.jpg");
		assertThat(response.isNewbie()).isFalse();
	}


}
