package com.example.be.core.web.question;

import com.example.be.core.application.QuestionService;
import com.example.be.core.web.QuestionController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@WebMvcTest(QuestionController.class)
public abstract class InitQuestionControllerTest {

  @Autowired
  protected MockMvc mockMvc;

  @MockBean
  protected QuestionService questionService;

  @Autowired
  protected ObjectMapper objectMapper;

  @BeforeEach
  void init(WebApplicationContext wc) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wc)
        .addFilter(new CharacterEncodingFilter("UTF-8", true))
        .build();
  }
}