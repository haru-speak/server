package com.example.be.core.application;

import com.example.be.core.application.dto.response.QuestionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class QuestionService {

    public QuestionResponse findById(Long questionId) {
        log.debug("[오늘의 문장 조회] questionId = {}", questionId);
        return null;
    }
}
