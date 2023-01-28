package com.example.be.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class MemberResponse {

  @Schema(type = "Long", description = "멤버 ID, NOT NULL")
  @NotNull
  @Positive
  private final Long memberId;

  @Schema(type = "String", description = "이름, NOT NULL")
  @NotBlank
  private final String nickname;

  @Schema(type = "String", description = "이메일, NOT NULL")
  @NotBlank
  private final String email;

  @Schema(type = "String", description = "비밀번호, NOT NULL")
  @NotBlank
  private final String password;

  @Schema(type = "String", description = "프로필 이미지, NOT NULL")
  private final String profileImage;

  public MemberResponse(Long memberId, String nickname, String email, String password, String profileImage) {
    this.memberId = memberId;
    this.nickname = nickname;
    this.email = email;
    this.password = password;
    this.profileImage = profileImage;
  }

}
