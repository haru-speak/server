package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class MemberResponse {

  @Schema(type = "Long", description = "멤버 ID, NOT NULL")
  private final Long memberId;

  @Schema(type = "String", description = "이름, NOT NULL")
  private final String nickname;

  @Schema(type = "String", description = "이메일, NULL")
  private final String email;

  @Schema(type = "String", description = "프로필 이미지, NULL")
  private final String profileImage;

  @Schema(type = "String", description = "유니크 ID")
  private final String uniqueId;

  public MemberResponse(Long memberId, String nickname, String email, String profileImage, String uniqueId) {
    this.memberId = memberId;
    this.nickname = nickname;
    this.email = email;
    this.profileImage = profileImage;
    this.uniqueId = uniqueId;
  }
}
