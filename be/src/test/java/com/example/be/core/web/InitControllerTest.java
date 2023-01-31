package com.example.be.core.web;

import com.example.be.common.config.WebConfig;
import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.application.StudyService;
import com.example.be.oauth.LoginArgumentResolver;
import com.example.be.oauth.OAuthInterceptor;
import com.example.be.oauth.config.properties.JwtProperties;
import com.example.be.oauth.provider.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@WebMvcTest(controllers = {SpeakingLogController.class, StudyController.class})
public abstract class InitControllerTest {

	@MockBean
	protected SpeakingLogService speakingLogService;

	@MockBean
	protected StudyService studyService;

	@MockBean
	protected WebConfig webConfig;

	@MockBean
	protected OAuthInterceptor oAuthInterceptor;

	@MockBean
	protected LoginArgumentResolver loginArgumentResolver;

	@MockBean
	protected JwtProvider jwtProvider;

	@MockBean
	protected JwtProperties jwtProperties;

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@BeforeEach
	void init(WebApplicationContext wc) {
		this.mockMvc = MockMvcBuilders
			.webAppContextSetup(wc)
			.addFilter(new CharacterEncodingFilter("UTF-8", true))
			.build();
	}
}
