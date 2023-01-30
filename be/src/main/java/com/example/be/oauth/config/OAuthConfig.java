package com.example.be.oauth.config;

import com.example.be.oauth.config.properties.KakaoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KakaoProperties.class)
public class OAuthConfig {

	private final KakaoProperties kakaoProperties;

	public OAuthConfig(KakaoProperties kakaoProperties) {
		this.kakaoProperties = kakaoProperties;
	}
}
