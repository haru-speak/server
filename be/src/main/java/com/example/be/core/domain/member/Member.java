package com.example.be.core.domain.member;

import com.example.be.core.domain.speakinglog.Comment;
import com.example.be.core.domain.speakinglog.Favorite;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
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
