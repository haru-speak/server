package com.example.be.oauth.provider;

import com.example.be.core.domain.member.Member;
import com.example.be.core.repository.member.MemberRepository;
import com.example.be.oauth.provider.dto.response.KakaoAccountResponse;
import com.example.be.oauth.provider.dto.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class LoginService {

	private static final String BASIC_PROFILE_IMAGE = "https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/b5ddbdeb-00bf-425a-9321-e36d25323bc0.jpg";
	private final KakaoProvider kakaoProvider;
	private final JwtProvider jwtProvider;
	private final MemberRepository memberRepository;

	public LoginService(KakaoProvider kakaoProvider, JwtProvider jwtProvider, MemberRepository memberRepository) {
		this.kakaoProvider = kakaoProvider;
		this.jwtProvider = jwtProvider;
		this.memberRepository = memberRepository;
	}

	@Transactional
	public LoginResponse login(final String code, final boolean appType) {
		KakaoAccountResponse memberInformation = kakaoProvider.getMemberInformation(code, appType);
		Boolean isNewbie = Boolean.FALSE;

		if (!memberRepository.existsByUniqueId(memberInformation.getUniqueId())){
			isNewbie = Boolean.TRUE;
		}

		Member member = memberRepository.findByUniqueId(memberInformation.getUniqueId())
			.orElseGet(() -> memberRepository.save(new Member(
				memberInformation.getNickname(),
				memberInformation.getEmail(),
				memberInformation.getUniqueId(),
				memberInformation.getProfileImage())));

		if (member.getProfileImage() == null) {
			member.updateProfileImage(BASIC_PROFILE_IMAGE);
		}

		return new LoginResponse(
			member.getId(),
			member.getNickname(),
			member.getProfileImage(),
			member.getEmail(),
			jwtProvider.generateAccessToken(member.getId()),
			jwtProvider.generateRefreshToken(member.getId()),
			isNewbie
		);
	}

	public String reIssueAccessToken(final Long memberId) {
		return jwtProvider.generateAccessToken(memberId);
	}
}
