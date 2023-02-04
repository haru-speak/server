package com.example.be.common.exception.member.goal;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class NotFoundGoalIdException extends BaseException {

	public NotFoundGoalIdException() {
		super(ErrorCodeAndMessages.GOAL_ID_NOT_FOUND_ERROR);
	}
}
