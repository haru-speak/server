package com.example.be.common.exception.member;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class NotFoundFollowRelationshipException extends BaseException {

	public NotFoundFollowRelationshipException() {
		super(ErrorCodeAndMessages.FOLLOW_RELATIONSHIP_NOT_FOUND_ERROR);
	}
}
