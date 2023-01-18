package com.example.be.common.config;

import com.example.be.core.domain.SpeakingLogType;
import org.springframework.core.convert.converter.Converter;

public class SpeakingLogTypeUpperCaseConverter implements Converter<String, SpeakingLogType> {

	@Override
	public SpeakingLogType convert(String source) {
		return SpeakingLogType.valueOf(source.toUpperCase());
	}
}
