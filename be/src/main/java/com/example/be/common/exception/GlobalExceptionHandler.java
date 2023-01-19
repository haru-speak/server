package com.example.be.common.exception;

import static com.example.be.common.exception.ErrorCodeAndMessages.SPEAKING_LOG_TYPE_ERROR;

import com.example.be.common.response.BaseResponse;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ConversionFailedException.class)
	public BaseResponse<String> handleConversionFailed(ConversionFailedException e) {
		return new BaseResponse<>(SPEAKING_LOG_TYPE_ERROR, null);
	}

}
