package com.example.be.core.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpeakingLogModifyRequest {

	@NotNull(message = "제목이 빈 값이어서는 안됩니다.")
	@Size(min = 4, max = 30, message = "제목은 4자 이상 ~ 30자 이내로 작성해야 합니다.")
	@Schema(type = "String", description = "변경할 제목, NOT NULL")
	private String title;

	@NotNull(message = "voiceRecord가 빈 값이어서는 안됩니다.")
	@Pattern(regexp = "^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/?([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$",
		message = "올바른 voiceRecord를 입력해야 합니다.")
	@Schema(type = "String", description = "변경할 음성 녹음 URL, NOT NULL")
	private String voiceRecord;

	@NotNull(message = "voiceText가 빈 값이어서는 안됩니다.")
	@Size(min = 2, message = "올바른 voiceText를 입력해야 합니다.")
	@Schema(type = "String", description = "변경할 음성 텍스트 URL, NOT NULL")
	private String voiceText;

	public SpeakingLogModifyRequest(String title, String voiceRecord, String voiceText) {
		this.title = title;
		this.voiceRecord = voiceRecord;
		this.voiceText = voiceText;
	}
}
