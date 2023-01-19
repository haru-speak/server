package com.example.be.core.application.dto.request;

import com.example.be.common.exception.speakinglog.InvalidSpeakingLogDateException;
import com.example.be.core.domain.SpeakingLogType;
import java.time.LocalDate;
import java.util.regex.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SpeakingLogConditionRequest {

	/**
	 * 정규식 해석 (ex : yyyyMMdd)
	 *  - (19|20) :  19 또는 20으로 year 앞 2자 표현
	 *  - \\d{2} : 2자리 숫자(00~99 까지) year 뒤 2자 표현
	 *  - (0[1-9]|1[012]) : 0과 1~9에서 1개 또는 1과 012에서 1개로 month 2자 표현
	 *  - (0[1-9]|[12][0-9]|3[01]) : 0과 1~9 또는 1,2와 0~9 또는 3과 0,1로 day 2자 표현
	 */

	@Schema(type = "String", description = "생년월일, NOT NULL", example = "YYYYMMDD")
	public static final String YYYYMMDD = "(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])";

	@Schema(enumAsRef = true, description = "스피킹 로그 조회 타입, NOT NULL")
	private final SpeakingLogType type;

	@Schema(type = "date", description = "날짜, NOT NULL")
	private final LocalDate date;

	public SpeakingLogConditionRequest(String type, String date) {
		this.type = getSpeakingLogTypeFromString(type);
		this.date = getLocalDateFromString(date);
	}

	private SpeakingLogType getSpeakingLogTypeFromString(String input) {

		if (input == null) {
			return SpeakingLogType.MY;
		}
		return SpeakingLogType.convert(input.toUpperCase());
	}
	private LocalDate getLocalDateFromString(String input) {

		if (input == null) {
			return LocalDate.now();
		}

		if (Pattern.matches(YYYYMMDD, input)) {
			return LocalDate.of(Integer.parseInt(input.substring(0, 4)),
				Integer.parseInt(input.substring(4, 6)),
				Integer.parseInt(input.substring(6, 8)));
		}
		throw new InvalidSpeakingLogDateException();
	}

}
