package com.example.be.core.application.dto.request;

import com.example.be.core.domain.member.MemberType;
import com.example.be.core.domain.member.SpeakingTestType;
import com.example.be.core.domain.member.grade.SpeakingGradeLanguage;
import com.example.be.core.domain.member.grade.SpeakingGradeLevel;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSignUpRequest {

	@NotNull(message = "memberType 을 정확히 입력해주세요. "
		+ "elementary_school, middle_school, high_school, university, office_worker, job_seeker, free 중 하나여야 합니다.")
	@Schema(enumAsRef = true, description = "멤버의 타입 선택, NOT NULL",
		allowableValues = {"\"elementary_school\"", "\"middle_school\"", "\"high_school\"",
			"\"university\"", "\"office_worker\"", "\"job_seeker\"", "\"free\""})
	private MemberType memberType;

	@NotNull(message = "learnerLanguage 를 정확히 입력해주세요. "
		+ "ENG, KOR 중 하나여야 합니다.")
	@Schema(enumAsRef = true, description = "Learner Language 선택, NOT NULL",
		allowableValues = {"\"ENG\"", "\"KOR\""})
	private SpeakingGradeLanguage learnerLanguage;

	@NotNull(message = "learnerLevel 을 정확히 입력해주세요. "
		+ "1, 2, 3, 4, 5 중 하나여야 합니다.")
	@Schema(enumAsRef = true, description = "Learner Language Level, NOT NULL",
		allowableValues = {"\"1\"", "\"2\"", "\"3\"", "\"4\"", "\"5\""})
	private SpeakingGradeLevel learnerLevel;

	@NotNull(message = "giverLanguage 를 정확히 입력해주세요. ENG, KOR 중 하나여야 합니다.")
	@Schema(enumAsRef = true, description = "Giver Language 선택, NOT NULL",
		allowableValues = {"\"ENG\"", "\"KOR\""})
	private SpeakingGradeLanguage giverLanguage;

	@NotNull(message = "giverLevel 을 정확히 입력해주세요. "
		+ "1, 2, 3, 4, 5 중 하나여야 합니다.")
	@Schema(enumAsRef = true, description = "Giver Language Level, NOT NULL",
		allowableValues = {"\"1\"", "\"2\"", "\"3\"", "\"4\"", "\"5\""})
	private SpeakingGradeLevel giverLevel;

	@NotNull(message = "하나 이상의 목표가 선택되어야 합니다.")
	@Size(min = 1, message = "하나 이상의 목표가 선택되어야 합니다.")
	@Schema(subTypes = {Long.class}, description = "목표 다중 선택 가능(0부터 순서대로 기입, 1개 이상), NOT NULL")
	private List<Long> goals;

	@NotNull(message = "셋 이상의 주제가 선택되어야 합니다.")
	@Size(min = 3, message = "셋 이상의 주제가 선택되어야 합니다.")
	@Schema(subTypes = {Long.class}, minLength = 3, description = "주제 다중 선택 가능(3개 이상), NOT NULL")
	private List<Long> subjects;

	@NotNull(message = "alarmStatus 를 정확히 입력해주세요. TRUE, FALSE 중 하나여야 합니다.")
	@Schema(type = "Boolean")
	private Boolean alarmStatus;


	@Schema(enumAsRef = true, description = "스피킹 시험의 종류(한 개만 선택 가능): OPIC, TOEFL, TOEIC_SPEAKING")
	private SpeakingTestType testType;

	public MemberSignUpRequest(String memberType, String learnerLanguage,
		String learnerLevel, String giverLanguage, String giverLevel, List<Long> goals,
		List<Long> subjects, Boolean alarmStatus, String testType) {
		this.memberType = MemberType.convert(memberType);
		this.learnerLanguage = SpeakingGradeLanguage.convert(learnerLanguage);
		this.learnerLevel = SpeakingGradeLevel.convert(learnerLevel);
		this.giverLanguage = SpeakingGradeLanguage.convert(giverLanguage);
		this.giverLevel = SpeakingGradeLevel.convert(giverLevel);
		this.goals = goals;
		this.subjects = subjects;
		this.alarmStatus = alarmStatus;
		this.testType = SpeakingTestType.convert(testType);
	}

	public void updateSpeakingTestType(String source) {
		this.testType = SpeakingTestType.convert(source);
	}
}
