package com.example.be.common.exception.study;

import com.example.be.common.exception.BaseException;
import com.example.be.common.exception.ErrorCodeAndMessages;

public class InvalidStudyPreviewTypeException extends BaseException {

  public InvalidStudyPreviewTypeException() {
    super(ErrorCodeAndMessages.STUDY_PREVIEW_TYPE_ERROR);
  }
}
