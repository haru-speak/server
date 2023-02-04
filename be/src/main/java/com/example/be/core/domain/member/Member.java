package com.example.be.core.domain.member;

import com.example.be.core.domain.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	private String nickname;

	private String introduce;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String uniqueId;

	private String profileImage;
	private MemberType memberType;
	private Boolean alarmStatus;
	private Integer point;
	private SpeakingTestType testType;

	public Member(String nickname, String email, String uniqueId, String profileImage) {
		this.nickname = nickname;
		this.email = email;
		this.uniqueId = uniqueId;
		this.profileImage = profileImage;
	}

	public void signUp(MemberType memberType, Boolean alarmStatus, SpeakingTestType testType) {
		this.memberType = memberType;
		this.alarmStatus = alarmStatus;
		this.point = 0;
		this.testType = testType;
	}

	public void updateProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public void modifyMyInformation(String nickname, String email, String profileImage, String introduce,
		MemberType memberType, Boolean alarmStatus, SpeakingTestType testType) {
		this.nickname = nickname;
		this.email = email;
		this.profileImage = profileImage;
		this.introduce = introduce;
		this.memberType = memberType;
		this.alarmStatus = alarmStatus;
		this.testType = testType;
	}
}
