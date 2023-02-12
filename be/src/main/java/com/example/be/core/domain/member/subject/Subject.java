package com.example.be.core.domain.member.subject;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject {

	@Id
	@Column(name = "subject_id")
	private Long id;

	private String content;

	private String image;

	public Subject(Long id, String content, String image) {
		this.id = id;
		this.content = content;
		this.image = image;
	}
}
