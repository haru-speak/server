package com.example.be.tool;

import com.example.be.common.config.WebConfig;
import com.example.be.oauth.LoginArgumentResolver;
import com.example.be.oauth.OAuthInterceptor;
import com.example.be.oauth.config.properties.JwtProperties;
import com.example.be.oauth.provider.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
@EnableConfigurationProperties(value = {JwtProperties.class})
public class TestWebConfig {

	@Autowired
	JwtProperties jwtProperties;

	@Bean
	WebConfig getWebconfig() {
		return new WebConfig(getOAuthInterceptor(), getLoginArgumentResolver());
	}

	@Bean
	@Primary
	OAuthInterceptor getOAuthInterceptor() {
		return new OAuthInterceptor(getJwtProvider());
	}

	@Bean
	@Primary
	LoginArgumentResolver getLoginArgumentResolver() {
		return new LoginArgumentResolver(getJwtProvider());
	}

	@Bean
	JwtProvider getJwtProvider() {
		return new JwtProvider(jwtProperties);
	}
}
