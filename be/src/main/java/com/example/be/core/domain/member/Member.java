package com.example.be.core.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	private String nickname;

	@Column(unique = true)
	private String email;

	private String password;

	@Lob
	private String profileImage;

	public Member(String nickname, String email, String password, String profileImage) {
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.profileImage = profileImage;
	}
}
