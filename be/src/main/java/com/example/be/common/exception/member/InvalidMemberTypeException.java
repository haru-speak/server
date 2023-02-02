package com.example.be.common.exception.member;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class InvalidMemberTypeException extends BaseException {

	public InvalidMemberTypeException() {
		super(ErrorCodeAndMessages.MEMBER_TYPE_ERROR);
	}
}
