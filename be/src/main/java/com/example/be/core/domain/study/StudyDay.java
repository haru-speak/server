package com.example.be.core.domain.study;

import lombok.Getter;
import lombok.ToString;

@Getter
public enum StudyDay {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private String studyDay;

    StudyDay(String studyDay) {
        this.studyDay = studyDay;
    }
}
