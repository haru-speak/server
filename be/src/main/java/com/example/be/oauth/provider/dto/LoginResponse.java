package com.example.be.oauth.provider.dto;

import lombok.Getter;

@Getter
public class LoginResponse {

	private final Long memberId;
	private final String nickname;
	private final String profileImage;
	private final String email;
	private final String accessToken;
	private final String refreshToken;

	private final boolean newbie;

	public LoginResponse(Long memberId, String nickname, String profileImage, String email,
		String accessToken, String refreshToken, boolean newbie) {
		this.memberId = memberId;
		this.nickname = nickname;
		this.profileImage = profileImage;
		this.email = email;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.newbie = newbie;
	}
}
