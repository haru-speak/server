package com.example.be.core.web.speakinglog;

import com.example.be.core.application.MemberService;
import com.example.be.core.web.MemberController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@WebMvcTest(MemberController.class)
public abstract class InitMemberControllerTest {

  @Autowired
  protected MockMvc mockMvc;

  @MockBean
  protected MemberService memberService;

  @Autowired
  protected ObjectMapper objectMapper;

  @BeforeEach
  void init(WebApplicationContext wc) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wc)
        .addFilter(new CharacterEncodingFilter("UTF-8", true))
        .build();
  }
}
