package com.example.be.core.domain.study;

import java.util.Objects;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StudyRegionConverter implements AttributeConverter<StudyRegion, String> {

    @Override
    public String convertToDatabaseColumn(StudyRegion attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.getRegion();
    }

    @Override
    public StudyRegion convertToEntityAttribute(String dbData) {
        if (dbData.isBlank()){
            return null;
        }
        return StudyRegion.valueOf(dbData);
    }
}
