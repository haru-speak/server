package com.example.be.core.domain.member;

import com.example.be.common.exception.member.InvalidMemberTypeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum MemberType {

	ELEMENTARY_SCHOOL("elementary_school"),
	MIDDLE_SCHOOL("middle_school"),
	HIGH_SCHOOL("high_school"),
	UNIVERSITY("university"),
	OFFICE_WORKER("office_worker"),
	JOB_SEEKER("job_seeker"),
	FREE("free")
	;

	private final String type;

	MemberType(String type) {
		this.type = type;
	}

	@JsonCreator
	public static MemberType convert(String source) {
		return Arrays.stream(MemberType.values())
			.filter(e -> e.type.equals(source.toLowerCase()))
			.findAny()
			.orElseThrow(InvalidMemberTypeException::new);
	}
}
