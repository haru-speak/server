package com.example.be.core.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

//	@OneToMany(mappedBy = "speakingLog")
//	private List<Favorite> favorites = new ArrayList<>();
//
//	@OneToMany(mappedBy = "speakingLog")
//	private List<Comment> comments = new ArrayList<>();


	public Member(String nickname) {
		this.nickname = nickname;
	}
}
