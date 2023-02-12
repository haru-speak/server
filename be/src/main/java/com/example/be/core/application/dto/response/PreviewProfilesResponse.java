package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
public class PreviewProfilesResponse {

    @Schema(type = "List<String>", description = "스터디에 참여중인 멤버들의 프로필 이미지, NOT NULL")
    private final List<String> memberProfiles;

    public PreviewProfilesResponse(List<String> memberProfiles) {
        this.memberProfiles = memberProfiles;
    }
}
