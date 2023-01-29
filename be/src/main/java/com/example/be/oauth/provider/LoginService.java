package com.example.be.oauth.provider;

import com.example.be.core.domain.member.Member;
import com.example.be.core.repository.member.MemberRepository;
import com.example.be.oauth.provider.dto.KakaoAccountResponse;
import com.example.be.oauth.provider.dto.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class LoginService {

	private final KakaoProvider kakaoProvider;
	private final JwtProvider jwtProvider;
	private final MemberRepository memberRepository;

	public LoginService(KakaoProvider kakaoProvider, JwtProvider jwtProvider, MemberRepository memberRepository) {
		this.kakaoProvider = kakaoProvider;
		this.jwtProvider = jwtProvider;
		this.memberRepository = memberRepository;
	}

	@Transactional
	public LoginResponse login(final String code) {
		KakaoAccountResponse memberInformation = kakaoProvider.getMemberInformation(code);
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

		return new LoginResponse(
			member.getId(),
			member.getNickname(),
			member.getProfileImage(),
			jwtProvider.generateAccessToken(member.getId()),
			jwtProvider.generateRefreshToken(member.getId()),
			isNewbie
		);
	}

	public String reIssueAccessToken(final Long memberId) {
		return jwtProvider.generateAccessToken(memberId);
	}
}
