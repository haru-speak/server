package com.example.be.core.domain.member;

import com.example.be.core.application.dto.request.MemberFormRequest;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
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

<<<<<<< HEAD
=======
	@Lob
>>>>>>> 2d6a7482972d67ed69236859542b27db0a829625
	private String profileImage;

	public Member(String nickname, String email, String password, String profileImage) {
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.profileImage = profileImage;
	}

	public static Member of(MemberFormRequest memberFormRequest) {
		return new Member(memberFormRequest.getNickname(), memberFormRequest.getEmail(), memberFormRequest.getPassword(), memberFormRequest.getProfileImage());
	}
}
