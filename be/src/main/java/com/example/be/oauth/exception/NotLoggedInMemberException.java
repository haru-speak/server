package com.example.be.oauth.exception;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class NotLoggedInMemberException extends BaseException {

	public NotLoggedInMemberException() {
		super(ErrorCodeAndMessages.NOT_LOGGED_IN_ERROR);
	}
}
