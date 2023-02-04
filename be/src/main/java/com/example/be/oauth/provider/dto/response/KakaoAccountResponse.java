package com.example.be.oauth.provider.dto.response;

import com.example.be.oauth.config.KakaoAccountResponseDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

@Getter
@JsonDeserialize(using = KakaoAccountResponseDeserializer.class)
public class KakaoAccountResponse {

	private String uniqueId;
	private String nickname;
	private String profileImage;
	private String email;

	public KakaoAccountResponse(String uniqueId, String nickname, String profileImage, String email) {
		this.uniqueId = uniqueId;
		this.nickname = nickname;
		this.profileImage = profileImage;
		this.email = email;
	}
}
