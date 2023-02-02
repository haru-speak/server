package com.example.be.core.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberModifyRequest {

	@NotBlank(message = "닉네임이 빈 값이어서는 안됩니다.")
	@Schema(type = "String", description = "변경할 닉네임, NOT NULL")
	private String nickname;

	@NotNull(message = "Profile Image 가 빈 값이어서는 안됩니다.")
	@Pattern(regexp = "^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/?([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$",
		message = "올바른 voiceRecord를 입력해야 합니다.")
	@Schema(type = "String", description = "변경할 이미지, NOT NULL")
	private String profileImage;
}
