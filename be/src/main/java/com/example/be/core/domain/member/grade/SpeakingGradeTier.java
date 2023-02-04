package com.example.be.core.domain.member.grade;

import lombok.Getter;

@Getter
public enum SpeakingGradeTier {

	NORMAL("일반"),
	EXPERT("고수");

	private final String tier;

	SpeakingGradeTier(String tier) {
		this.tier = tier;
	}
}
