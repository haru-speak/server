package com.example.be.core.web.study;

import com.example.be.core.application.StudyService;
import com.example.be.core.web.StudyController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@WebMvcTest(StudyController.class)
public abstract class InitStudyControllerTest {

  @Autowired
  protected MockMvc mockMvc;

  @MockBean
  protected StudyService studyService;

  @Autowired
  protected ObjectMapper objectMapper;

  @BeforeEach
  void init(WebApplicationContext wc) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wc)
        .addFilter(new CharacterEncodingFilter("UTF-8", true))
        .build();
  }
}
