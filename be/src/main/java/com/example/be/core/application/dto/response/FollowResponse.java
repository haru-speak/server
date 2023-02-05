package com.example.be.core.application.dto.response;

import lombok.Getter;

@Getter
public class FollowResponse {

	private final Long followerId;
	private final Long followingId;

	public FollowResponse(Long followerId, Long followingId) {
		this.followerId = followerId;
		this.followingId = followingId;
	}
}
