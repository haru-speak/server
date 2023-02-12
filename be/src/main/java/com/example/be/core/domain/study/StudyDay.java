package com.example.be.core.domain.study;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum StudyDay {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private final String studyDay;

    StudyDay(String studyDay) {
        this.studyDay = studyDay;
    }

    public static StudyDay convert(String source) {
        return Arrays.stream(values())
            .filter(v -> v.studyDay.equals(source))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
}
