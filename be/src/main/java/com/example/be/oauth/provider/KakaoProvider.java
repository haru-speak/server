package com.example.be.oauth.provider;

import com.example.be.oauth.config.properties.KakaoProperties;
import com.example.be.oauth.provider.client.KakaoMemberInfoClient;
import com.example.be.oauth.provider.client.KakaoTokenClient;
import com.example.be.oauth.provider.dto.response.KakaoAccessTokenResponse;
import com.example.be.oauth.provider.dto.response.KakaoAccountResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KakaoProvider {

	private final KakaoTokenClient tokenClient;
	private final  KakaoMemberInfoClient memberInfoClient;
	private final KakaoProperties properties;

	public KakaoProvider(KakaoTokenClient tokenClient, KakaoMemberInfoClient memberInfoClient, KakaoProperties properties) {
		this.tokenClient = tokenClient;
		this.memberInfoClient = memberInfoClient;
		this.properties = properties;
	}


	public KakaoAccountResponse getMemberInformation(String code, boolean appType) {
		String accessToken = code;
		if (!appType) {
			KakaoAccessTokenResponse response = getAccessToken(code);
			accessToken = response.getAccessToken();
		}
		return memberInfoClient.call(properties.getContentType(), String.format("%s %s",
				properties.getTokenType(),
				accessToken));
	}

	private KakaoAccessTokenResponse getAccessToken(String authorizeCode) {
		return tokenClient.call(
			properties.getContentType(),
			properties.getGrantType(),
			properties.getClientId(),
			properties.getRedirectUri(),
			authorizeCode
		);
	}

}
