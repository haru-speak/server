package com.example.be.common.config;

import com.example.be.oauth.LoginArgumentResolver;
import com.example.be.oauth.OAuthInterceptor;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final OAuthInterceptor oAuthInterceptor;
	private final LoginArgumentResolver loginArgumentResolver;

	public WebConfig(OAuthInterceptor oAuthInterceptor, LoginArgumentResolver loginArgumentResolver) {
		this.oAuthInterceptor = oAuthInterceptor;
		this.loginArgumentResolver = loginArgumentResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(oAuthInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns(
				"/error/**",
				"/login/**",
				"/swagger-ui/**",
				"/swagger-resources/**",
				"/v3/api-docs/**",
				"/favicon.ico"
			);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(loginArgumentResolver);
	}
}
