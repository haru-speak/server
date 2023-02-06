package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
public class MemberProfilesResponse {

    @Schema(type = "String", description = "스터디 리더 이미지, NOT NULL")
    private final String leader;

    @Schema(type = "String", description = "스터디 리더가 아닌 멤버들의 이미지, NOT NULL")
    private final List<String> otherMembers;

    public MemberProfilesResponse(String leader, List<String> otherMembers) {
        this.leader = leader;
        this.otherMembers = otherMembers;
    }
}
