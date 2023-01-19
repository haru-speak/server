package com.example.be.common.response;

import com.example.be.common.CodeAndMessages;
import lombok.Getter;

@Getter
public class BaseResponse<T>  {

	private final String code;
	private final String message;
	private final T data;

	public BaseResponse(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public BaseResponse(CodeAndMessages codeAndMessages, T data) {
		this.code = codeAndMessages.getCode();
		this.message = codeAndMessages.getMessage();
		this.data = data;
	}
}
