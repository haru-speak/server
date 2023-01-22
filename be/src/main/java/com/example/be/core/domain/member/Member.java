package com.example.be.core.domain.member;

import com.example.be.core.application.dto.request.MemberFormRequest;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

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

	public Member(String nickname, String email, String password) {
		this.nickname = nickname;
		this.email = email;
		this.password = password;
	}

	public static Member of(MemberFormRequest memberFormRequest, PasswordEncoder passwordEncoder) {
		return new Member(memberFormRequest.getNickname(), memberFormRequest.getEmail(), passwordEncoder.encode(memberFormRequest.getPassword()));
	}
}
