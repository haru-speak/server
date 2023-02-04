package com.example.be.core.domain.member.grade;

import com.example.be.core.domain.member.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpeakingGrade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "speaking_grade_id")
	private Long id;

	private SpeakingGradeType type;

	private SpeakingGradeLanguage language;

	private SpeakingGradeLevel level;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	public SpeakingGrade(SpeakingGradeType type, SpeakingGradeLanguage language,
		SpeakingGradeLevel level, Member member) {
		this.type = type;
		this.language = language;
		this.level = level;
		this.member = member;
	}

	public void modify(SpeakingGradeLanguage language, SpeakingGradeLevel level) {
		this.language = language;
		this.level = level;
	}

	@Override
	public String toString() {
		return "type: " + this.type + ", language: " + this.language
			+ ", level: " + this.level + ", memberId: " + this.member.getId();
	}
}
