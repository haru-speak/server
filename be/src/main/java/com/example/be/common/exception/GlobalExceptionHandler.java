package com.example.be.common.exception;

import static com.example.be.common.exception.ErrorCodeAndMessages.BAD_REQUEST_ERROR;

import com.example.be.common.response.BaseResponse;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 400 BAD REQUEST : Parameter
	 */
	@ExceptionHandler(BindException.class)
	private BaseResponse<Void> handleBadRequest(BindException e) {
		String errorMessage = e.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(DefaultMessageSourceResolvable::getDefaultMessage)
			.collect(Collectors.joining("\n"));
		log.error("BindException: {}", errorMessage);
		return BaseResponse.error(BAD_REQUEST_ERROR.getCode(), errorMessage);
	}

	/**
	 * 400 BAD REQUEST : Path Variable
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	private BaseResponse<Void> handleBadRequest(ConstraintViolationException e) {
		log.error("ConstraintViolationException: {}", e.getMessage());
		return BaseResponse.error(BAD_REQUEST_ERROR.getCode(), e.getMessage());
	}


	@ExceptionHandler(BaseException.class)
	public BaseResponse<Void> handleConversionFailed(BaseException e) {
		log.warn("Error Code={}, Error Message={}", e.getCode(), e.getMessage());
		return BaseResponse.error(e.getCode(), e.getMessage());
	}

}
