package com.example.be.common.exception;

import com.example.be.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BaseException.class)
	public BaseResponse<String> handleConversionFailed(BaseException e) {
		log.info("Error Code={}, Error Message={}", e.getCode(), e.getMessage());
		return new BaseResponse<>(e.getCode(), e.getMessage(), null);
	}

}
