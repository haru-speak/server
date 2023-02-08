package com.example.be.core.web.member;


import static com.example.be.common.response.ResponseCodeAndMessages.CANCEL_FOLLOW_MEMBER_SUCCESS;
import static com.example.be.common.response.ResponseCodeAndMessages.FOLLOW_MEMBER_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.member.MemberService;
import com.example.be.core.application.dto.response.FollowResponse;
import com.example.be.core.web.InitControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(MemberController.class)
@DisplayName("컨트롤러 테스트 : Member 팔로우 요청 및 취소")
class MemberControllerFollowTest extends InitControllerTest {

	@MockBean
	private MemberService memberService;

	@Nested
	@DisplayName("Member 팔로우 요청을 할 때")
	class FollowTest {

		@Nested
		@DisplayName("정상적인 요청이라면")
		class NormalTest {

			@Test
			@DisplayName("로그인 한 멤버의 팔로우 관계가 생긴다.")
			void follow_member() throws Exception {
				//given
				Long memberId = 1L;
				Long followingId = 10L;

				FollowResponse response = new FollowResponse(memberId, followingId);

				BaseResponse<FollowResponse> baseResponse = new BaseResponse<>(FOLLOW_MEMBER_SUCCESS, response);
				when(memberService.follow(refEq(memberId), refEq(followingId)))
					.thenReturn(response);

				//when
				ResultActions resultActions = mockMvc.perform(post("/member/follow/{followingId}", followingId)
					.header("Authorization", "Bearer "+jwtProvider.generateAccessToken(memberId))
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON_VALUE));

				//then
				resultActions.andExpect(status().isOk())
					.andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
					.andDo(print());
			}
		}
	}

	@Nested
	@DisplayName("Member 팔로우 취소를 할 때")
	class CancelFollowTest {

		@Nested
		@DisplayName("정상적인 요청이라면")
		class NormalTest {

			@Test
			@DisplayName("로그인 한 멤버의 팔로우 관계가 삭제된다.")
			void cancel_follow_member() throws Exception {
				//given
				Long memberId = 1L;
				Long followingId = 10L;

				FollowResponse response = new FollowResponse(memberId, followingId);

				BaseResponse<FollowResponse> baseResponse = new BaseResponse<>(CANCEL_FOLLOW_MEMBER_SUCCESS, response);
				when(memberService.cancelFollow(refEq(memberId), refEq(followingId)))
					.thenReturn(response);

				//when
				ResultActions resultActions = mockMvc.perform(delete("/member/follow/{followingId}", followingId)
					.header("Authorization", "Bearer "+jwtProvider.generateAccessToken(memberId))
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON_VALUE));

				//then
				resultActions.andExpect(status().isOk())
					.andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
					.andDo(print());
			}
		}
	}
}
