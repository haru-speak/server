package com.example.be.core.web;

import static com.example.be.common.response.ResponseCodeAndMessages.JOIN_SUCCESS;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.MemberService;

import com.example.be.core.application.dto.request.MemberFormRequest;
import com.example.be.core.application.dto.response.MemberResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MemberController {

  private final MemberService memberService;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @PostMapping("/join")
  @ApiOperation(value = "회원가입에 따른 멤버 생성입니다.")
  public BaseResponse<MemberResponse> join(@RequestBody final MemberFormRequest memberFormRequest) {
    MemberResponse response = memberService.create(memberFormRequest);
    return new BaseResponse<>(JOIN_SUCCESS, response);
  }

}
