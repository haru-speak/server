package com.example.be.oauth;

import com.example.be.oauth.provider.JwtProvider;
import feign.Request.HttpMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class OAuthInterceptor implements HandlerInterceptor {

	private static final String AUTH_TOKEN = "AUTH_TOKEN";

	private final JwtProvider jwtProvider;

	public OAuthInterceptor(JwtProvider jwtProvider) {
		this.jwtProvider = jwtProvider;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (isPreflightRequest(request)) {
			return true;
		}

		String token = OAuthExtractor.extract(request);
		log.debug("REQUEST URI={}", request.getRequestURI());
		boolean isValidate = jwtProvider.validateToken(token);
		request.setAttribute(AUTH_TOKEN, token);
		log.debug("TOKEN VALIDATION STATUS = {}", isValidate);
		return isValidate;
	}

	private boolean isPreflightRequest(HttpServletRequest request) {
		return request.getMethod().equals(HttpMethod.OPTIONS.name());
	}
}
