package com.example.be.oauth.controller;

import static com.example.be.common.response.ResponseCodeAndMessages.OAUTH_LOGIN_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.REISSUE_ACCESS_TOKEN_SUCCESS;

import com.example.be.common.response.BaseResponse;
import com.example.be.oauth.Login;
import com.example.be.oauth.provider.LoginService;
import com.example.be.oauth.provider.dto.LoginResponse;
import io.swagger.annotations.ApiOperation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class LoginController {

	private final LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@GetMapping("/login/kakao")
	@ApiOperation(value = "OAuth 로그인입니다. (현재는 카카오 로그인만 지원)", hidden = true)
	public BaseResponse<LoginResponse> login(@RequestParam @NotBlank final String code) {
		LoginResponse response = loginService.login(code);
		return new BaseResponse<>(OAUTH_LOGIN_SUCCESS, response);
	}

	@GetMapping("/oauth/re-issue")
	@ApiOperation(value = "Access Token 재발급입니다. (Refresh Token 의 유효성 검증 이후 발급됩니다.)")
	public BaseResponse<String> reIssueAccessToken(@Login @Positive final Long memberId) {
		String response = loginService.reIssueAccessToken(memberId);
		return new BaseResponse<>(REISSUE_ACCESS_TOKEN_SUCCESS, response);
	}
}
