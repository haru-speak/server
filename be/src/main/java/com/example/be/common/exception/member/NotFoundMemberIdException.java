package com.example.be.common.exception.member;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class NotFoundMemberIdException extends BaseException {

	public NotFoundMemberIdException() {
		super(ErrorCodeAndMessages.LOGIN_MEMBER_NOT_FOUND_ERROR);
	}
}
