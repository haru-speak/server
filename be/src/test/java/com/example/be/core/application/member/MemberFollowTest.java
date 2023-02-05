package com.example.be.core.application.member;

import static com.example.be.common.exception.ErrorCodeAndMessages.MEMBER_NOT_FOUND_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.be.common.exception.ErrorCodeAndMessages;
import com.example.be.common.exception.member.NotFoundFollowRelationshipException;
import com.example.be.common.exception.member.NotFoundMemberIdException;
import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.MemberService;
import com.example.be.core.application.dto.response.FollowResponse;
import com.example.be.core.domain.member.Follow;
import com.example.be.core.repository.member.FollowRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("서비스 테스트 : Member Follow 요청 및 취소 테스트")
class MemberFollowTest extends InitServiceTest {

	@Autowired
	private MemberService memberService;

	@Autowired
	private FollowRepository followRepository;

	@Nested
	@DisplayName("Member Follow 요청을 할 때")
	class FollowTest {

		@Nested
		@DisplayName("정상적인 요청이라면")
		class NormalTest {

			@Test
			@DisplayName("followingId 를 받아 Follow 요청이 완료된다.")
			void success_member_follow(){
				//given
				Long memberId = 1L;
				Long followingId = 3L;
				List<Follow> beforeFollow = followRepository.findAll();

				//when
				FollowResponse followResponse = memberService.follow(memberId, followingId);
				List<Follow> afterFollow = followRepository.findAll();

				//then
				assertThat(followResponse.getFollowerId()).isEqualTo(memberId);
				assertThat(followResponse.getFollowingId()).isEqualTo(followingId);
				assertThat(afterFollow).hasSize(beforeFollow.size() + 1);
			}
		}

		@Nested
		@DisplayName("비정상적인 요청이라면")
		class AbnormalTest {

			@Test
			@DisplayName("followingId를 찾을 수 없을 때, NotFoundMemberIdException 이 나온다.")
			void fail_member_follow_with_not_found_following_id() {
				//given
				Long memberId = 1L;
				Long followingId = 777L;

				//when&then
				assertThatThrownBy(() -> memberService.follow(memberId, followingId))
					.hasMessage(MEMBER_NOT_FOUND_ERROR.getMessage())
					.isExactlyInstanceOf(NotFoundMemberIdException.class);
			}
		}
	}

	@Nested
	@DisplayName("Member Follow 취소를 할 때")
	class CancelFollowTest {

		@Nested
		@DisplayName("정상적인 요청이라면")
		class NormalTest {

			@Test
			@DisplayName("followingId 를 받아 Follow 취소 요청이 완료된다.")
			void success_member_cancel_follow(){
				//given
				Long memberId = 1L;
				Long followingId = 3L;
				List<Follow> beforeFollow = followRepository.findAll();

				//when
				memberService.follow(memberId, followingId);
				List<Follow> beforeFollowCancel = followRepository.findAll();
				FollowResponse followResponse = memberService.cancelFollow(memberId, followingId);
				List<Follow> afterFollowCancel = followRepository.findAll();

				//then
				assertThat(followResponse.getFollowerId()).isEqualTo(memberId);
				assertThat(followResponse.getFollowingId()).isEqualTo(followingId);
				assertThat(beforeFollow).hasSize(beforeFollowCancel.size() - 1);
				assertThat(beforeFollow).hasSize(afterFollowCancel.size());
				assertThat(beforeFollowCancel).hasSize(afterFollowCancel.size() + 1);
			}
		}

		@Nested
		@DisplayName("비정상적인 요청이라면")
		class AbnormalTest {

			@Test
			@DisplayName("팔로우 관계가 존재하지 않을 때, NotFoundFollowRelationshipException 이 나온다.")
			void success_member_cancel_follow(){
				//given
				Long memberId = 1L;
				Long followingId = 3L;

				//when&then
				assertThatThrownBy(() -> memberService.cancelFollow(memberId, followingId))
					.hasMessage(ErrorCodeAndMessages.FOLLOW_RELATIONSHIP_NOT_FOUND_ERROR.getMessage())
					.isExactlyInstanceOf(NotFoundFollowRelationshipException.class);

			}
		}
	}
}
