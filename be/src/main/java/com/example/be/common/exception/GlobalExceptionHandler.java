package com.example.be.common.exception;

import static com.example.be.common.exception.ErrorCodeAndMessages.BAD_REQUEST_ERROR;

import com.example.be.common.response.BaseResponse;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 400 BAD REQUEST
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	private BaseResponse<Void> handleBadRequest(HttpMessageNotReadableException e) {
		Throwable mostSpecificCause = e.getMostSpecificCause();
		String errorMessage = e.getMessage();
		if (mostSpecificCause != null) {
			errorMessage = mostSpecificCause.getMessage();
		}
		return BaseResponse.error(BAD_REQUEST_ERROR.getCode(), errorMessage);
	}

	/**
	 * 400 BAD REQUEST : Binding Parameter
	 */
	@ExceptionHandler(BindException.class)
	private BaseResponse<Void> handleBadRequestWithBindingParameter(BindException e) {
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
	private BaseResponse<Void> handleBadRequestWithPathVariable(ConstraintViolationException e) {
		log.error("ConstraintViolationException: {}", e.getMessage());
		return BaseResponse.error(BAD_REQUEST_ERROR.getCode(), e.getMessage());
	}

	/**
	 * 400 BAD REQUEST : HTTP Method
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	private BaseResponse<Void> handleBadRequestWithHttpMethod(HttpRequestMethodNotSupportedException e) {
		log.error("HttpRequestMethodNotSupportedException: {}", e.getMessage());
		return BaseResponse.error(BAD_REQUEST_ERROR.getCode(), e.getMessage());
	}

	@ExceptionHandler(BaseException.class)
	public BaseResponse<Void> handleConversionFailed(BaseException e) {
		log.warn("Error Code={}, Error Message={}", e.getCode(), e.getMessage());
		return BaseResponse.error(e.getCode(), e.getMessage());
	}

}
