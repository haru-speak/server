package com.example.be.common.exception.study;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class InvalidStudyTypeException extends BaseException {

  public InvalidStudyTypeException() {
    super(ErrorCodeAndMessages.STUDY_TYPE_ERROR);
  }
}
