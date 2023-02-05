package com.example.be.core.web;

import static com.example.be.common.response.ResponseCodeAndMessages.CANCEL_FOLLOW_MEMBER_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.FIND_MEMBER_INFO_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.FOLLOW_MEMBER_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.MODIFY_MEMBER_INFO_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.SIGN_UP_MEMBER_SUCCESS;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.MemberService;
import com.example.be.core.application.dto.request.MemberModifyRequest;
import com.example.be.core.application.dto.request.MemberSignUpRequest;
import com.example.be.core.application.dto.response.FollowResponse;
import com.example.be.core.application.dto.response.MemberResponse;
import com.example.be.core.application.dto.response.MemberSignUpResponse;
import com.example.be.oauth.Login;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping
	@ApiOperation(value = "멤버의 회원 등록입니다. [RequestBody : MemberSignUpRequest]")
	public BaseResponse<MemberSignUpResponse> signUp(@Login @Positive final Long memberId,
		@RequestBody @Valid final MemberSignUpRequest memberSignUpRequest) {
		MemberSignUpResponse response = memberService.signUp(memberId, memberSignUpRequest);
		return new BaseResponse<>(SIGN_UP_MEMBER_SUCCESS, response);
	}

	@PutMapping
	@ApiOperation(value = "멤버의 회원 정보 수정입니다. [RequestBody: MemberModifyRequest]")
	public BaseResponse<MemberResponse> modify(@Login @Positive final Long memberId,
		@RequestBody @Valid final MemberModifyRequest memberModifyRequest) {
		MemberResponse response = memberService.modify(memberId, memberModifyRequest);
		return new BaseResponse<>(MODIFY_MEMBER_INFO_SUCCESS, response);
	}

	@GetMapping("/mypage")
	@ApiOperation(value = "멤버의 마이 페이지 정보입니다.")
	public BaseResponse<MemberResponse> myPage(@Login @Positive final Long memberId) {
		MemberResponse response = memberService.findById(memberId);
		return new BaseResponse<>(FIND_MEMBER_INFO_SUCCESS, response);
	}

	@PostMapping("/follow/{followingId}")
	@ApiOperation(value = "멤버의 팔로우 요청입니다. (현재는 무조건 허용으로 진행됩니다.)")
	public BaseResponse<FollowResponse> follow(@Login @Positive final Long memberId,
		@PathVariable @Positive final Long followingId) {
		FollowResponse response = memberService.follow(memberId, followingId);
		return new BaseResponse<>(FOLLOW_MEMBER_SUCCESS, response);
	}

	@DeleteMapping("/follow/{followingId}")
	@ApiOperation(value = "멤버의 팔로우 취소입니다.")
	public BaseResponse<FollowResponse> cancelFollow(@Login @Positive final Long memberId,
		@PathVariable @Positive final Long followingId) {
		FollowResponse response = memberService.cancelFollow(memberId, followingId);
		return new BaseResponse<>(CANCEL_FOLLOW_MEMBER_SUCCESS, response);
	}
}

