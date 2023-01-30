package com.example.be.oauth.exception;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class InvalidJwtException extends BaseException {

	public InvalidJwtException() {
		super(ErrorCodeAndMessages.INVALID_JWT_TOKEN_ERROR);
	}
}
