package com.example.be.oauth.provider.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class KakaoAccessTokenResponse {

	private String tokenType;
	private String accessToken;
	private Long expiresIn;
	private String refreshToken;
	private Long refreshTokenExpiresIn;

}
