package com.example.be.core.application;

import com.example.be.core.application.dto.response.QuestionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@Service
public class QuestionService {


    public QuestionResponse find() {
        return null;
    }
}
