package com.example.be.core.application.dto.request;

import com.example.be.common.exception.speakinglog.InvalidSpeakingLogDateException;
import com.example.be.core.domain.speakinglog.SpeakingLogType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import javax.validation.constraints.PastOrPresent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpeakingLogConditionRequest {

	/**
	 * 정규식 해석 (ex : yyyyMMdd)
	 *  - (19|20) :  19 또는 20으로 year 앞 2자 표현
	 *  - \\d{2} : 2자리 숫자(00~99 까지) year 뒤 2자 표현
	 *  - (0[1-9]|1[012]) : 0과 1~9에서 1개 또는 1과 012에서 1개로 month 2자 표현
	 *  - (0[1-9]|[12][0-9]|3[01]) : 0과 1~9 또는 1,2와 0~9 또는 3과 0,1로 day 2자 표현
	 */

	public static final String YYYYMMDD = "(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])";

	@Schema(enumAsRef = true, description = "스피킹 로그 조회 타입, NULL, default = type.my")
	private SpeakingLogType type;

	@PastOrPresent(message = "과거 혹은 오늘까지의 날짜만 가능합니다.")
	@Schema(type = "date", description = "날짜, NULL, default = today")
	private LocalDate date;

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
			return LocalDate.parse(input, DateTimeFormatter.BASIC_ISO_DATE);
		}
		throw new InvalidSpeakingLogDateException();
	}

}
