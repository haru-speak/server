package com.example.be.core.application.dto.response;

import com.example.be.core.domain.member.MemberType;
import com.example.be.core.domain.member.SpeakingTestType;
import com.example.be.core.domain.member.grade.SpeakingGradeLanguage;
import com.example.be.core.domain.member.grade.SpeakingGradeLevel;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MemberSignUpResponse {

	private final Long memberId;
	private final MemberType memberType;
	private final SpeakingGradeLanguage learnerLanguage;
	private final SpeakingGradeLevel learnerLevel;
	private final SpeakingGradeLanguage giverLanguage;
	private final SpeakingGradeLevel giverLevel;
	private final List<GoalResponse> goals;
	private final List<SubjectResponse> subjects;
	private final Boolean alarmStatus;
	private final SpeakingTestType testType;

	public MemberSignUpResponse(Long memberId, MemberType memberType,
		SpeakingGradeLanguage learnerLanguage, SpeakingGradeLevel learnerLevel,
		SpeakingGradeLanguage giverLanguage, SpeakingGradeLevel giverLevel,
		List<GoalResponse> goals, List<SubjectResponse> subjects,
		Boolean alarmStatus, SpeakingTestType testType) {
		this.memberId = memberId;
		this.memberType = memberType;
		this.learnerLanguage = learnerLanguage;
		this.learnerLevel = learnerLevel;
		this.giverLanguage = giverLanguage;
		this.giverLevel = giverLevel;
		this.goals = goals;
		this.subjects = subjects;
		this.alarmStatus = alarmStatus;
		this.testType = testType;
	}
}
