package com.example.be.core.web;

import com.example.be.core.application.SpeakingLogService;
import com.example.be.core.application.StudyService;
import com.example.be.oauth.provider.JwtProvider;
import com.example.be.tool.TestWebConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@ActiveProfiles("test")
@Import(TestWebConfig.class)
@WebMvcTest(controllers = {SpeakingLogController.class, StudyController.class, MemberController.class})
public abstract class InitControllerTest {

	@MockBean
	protected SpeakingLogService speakingLogService;

	@MockBean
	protected StudyService studyService;

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected JwtProvider jwtProvider;

	@BeforeEach
	void init(WebApplicationContext wc) {
		this.mockMvc = MockMvcBuilders
			.webAppContextSetup(wc)
			.addFilter(new CharacterEncodingFilter("UTF-8", true))
			.build();
	}
}
