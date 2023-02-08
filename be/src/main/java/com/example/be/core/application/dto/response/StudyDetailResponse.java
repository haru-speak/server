package com.example.be.core.application.dto.response;

import com.example.be.core.domain.study.StudyRegion;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class StudyDetailResponse {

  @Schema(type = "Long", description = "스터디 ID, NOT NULL")
  @Positive
  private final Long studyId;

  @Schema(type = "String", description = "제목, NOT NULL")
  private final String title;

  @Schema(type = "String", description = "내용, NOT NULL")
  private final String content;

  @Schema(type = "int", description = "레벨, NOT NULL")
  private final Integer level;

  @Schema(type = "String", description = "언어, NOT NULL")
  private final String language;

  @Schema(type = "String", description = "스피킹 시험, NOT NULL")
  private final String speakingTest;

  @Schema(type = "String", description = "스피킹 시험에서 받는 등급, NOT NULL")
  private final String grade;

  @Schema(type = "int", description = "최대 인원, NOT NULL")
  private final Integer maxCapacity;

  @Schema(type = "int", description = "최소 인원, NOT NULL")
  private final Integer minCapacity;

  @Schema(type = "String", description = "대면, 비대면 규칙, NOT NULL")
  private final String rule;

  @Schema(type = "StudyRegion", description = "지역, NULL")
  private final StudyRegion region;

  @Schema(type = "String", description = "스터디 하는 요일, NOT NULL")
  private final String studyDay;

  @Schema(type = "String", description = "포스터 이미지, NOT NULL")
  private final String posterImage;

  @Schema(type = "Int", description = "관심 개수, NOT NULL")
  private final Integer interestCount;

  @Schema(type = "Boolean", description = "관심 여부, NOT NULL")
  private final Boolean isInterested;

  @Schema(type = "Boolean", description = "조회를 한 현재 유저가 스터디 리더인지 여부, NOT NULL")
  private final Boolean isLeader;

  @Schema(type = "MemberProfilesResponse", description = "스터디에 참여중인 멤버들의 프로필 이미지, NOT NULL")
  private final MemberProfilesResponse memberProfiles;

  public StudyDetailResponse(Long studyId, String title, String content, Integer level,
      String language, String speakingTest, String grade, Integer maxCapacity, Integer minCapacity,
      String rule, StudyRegion region, String studyDay, String posterImage, Integer interestCount,
      Boolean isInterested, Boolean isLeader, MemberProfilesResponse memberProfiles) {
    this.studyId = studyId;
    this.title = title;
    this.content = content;
    this.level = level;
    this.language = language;
    this.speakingTest = speakingTest;
    this.grade = grade;
    this.maxCapacity = maxCapacity;
    this.minCapacity = minCapacity;
    this.rule = rule;
    this.region = region;
    this.studyDay = studyDay;
    this.posterImage = posterImage;
    this.interestCount = interestCount;
    this.isInterested = isInterested;
    this.isLeader = isLeader;
    this.memberProfiles = memberProfiles;
  }
}
