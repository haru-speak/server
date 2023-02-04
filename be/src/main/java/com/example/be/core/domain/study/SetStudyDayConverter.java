package com.example.be.core.domain.study;

import java.util.Arrays;
import java.util.EnumSet;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
@Converter
public class SetStudyDayConverter implements AttributeConverter<EnumSet<StudyDay>, String> {

    @Override
    public String convertToDatabaseColumn(EnumSet<StudyDay> attribute) {
        StringBuilder sb = new StringBuilder();
        attribute.stream().forEach(e -> sb.append(e.name() + ","));
        String result = sb.toString();
        if (result.charAt(result.length() - 1) == ',') result = result.substring(0, result.length() - 1);
        log.debug("[DB 에서 사용될 StudyDay 정보] : {}", result);
        return result;
    }

    @Override
    public EnumSet<StudyDay> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData == "" || dbData.contains(".")) return EnumSet.noneOf(StudyDay.class);
        EnumSet<StudyDay> attribute = EnumSet.noneOf(StudyDay.class);
        String[] dbDataArray = StringUtils.trimAllWhitespace(dbData).toUpperCase().split(",");
        Arrays.stream(dbDataArray).forEach(e -> attribute.add(StudyDay.valueOf(e)));
        log.debug("[Entity 에서 사용될 StudyDay 정보] : {}", attribute);
        return attribute;
    }
}
