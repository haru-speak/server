package com.example.be.core.domain.study;

import lombok.Getter;

@Getter
public enum StudyRegion {
    SEOUL("서울"),
    SUWON("수원"),
    INCHEON("인천"),
    DAEGU("대구"),
    BUSAN("부산"),
    ULSAN("울산"),
    GWANGJU("광주"),
    JEONJU("전주"),
    DAEJEON("대전"),
    SEJONG("세종"),
    CHEONAN("천안"),
    CHEONGJU("청주"),
    WONJU("원주"),
    CHUNCHEON("춘천"),
    JEJU("제주"),
    ETC("기타");

    private String region;

    StudyRegion(String region) {
        this.region = region;
    }
}
