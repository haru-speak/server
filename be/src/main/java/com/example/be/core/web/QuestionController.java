package com.example.be.core.web;

import static com.example.be.common.response.ResponseCodeAndMessages.FIND_QUESTION_SUCCESS;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.QuestionService;
import com.example.be.core.application.dto.response.QuestionResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    @ApiOperation(value = "오늘의 문장 조회입니다.")
    public BaseResponse<QuestionResponse> find() {
        QuestionResponse response = questionService.find();
        return new BaseResponse<>(FIND_QUESTION_SUCCESS, response);
    }
}
