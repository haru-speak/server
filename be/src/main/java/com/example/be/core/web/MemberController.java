package com.example.be.core.web;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.MemberService;

import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MemberController {

  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;

  public MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
    this.memberService = memberService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping
  @ApiOperation(value = "회원가입에 따른 멤버 생성입니다.")
  public BaseResponse<> join() {

  }

}
