package com.example.be.core.application.dto.response;

import com.example.be.core.domain.member.MemberType;
import com.example.be.core.domain.member.SpeakingTestType;
import com.example.be.core.domain.member.grade.SpeakingGradeLanguage;
import com.example.be.core.domain.member.grade.SpeakingGradeLevel;
import java.util.List;
import lombok.Getter;

@Getter
public class MemberResponse {

  private final Long memberId;
  private final String nickname;
  private final String email;
  private final String profileImage;
  private final String introduce;
  private final MemberType memberType;
  private final SpeakingGradeLanguage learnerLanguage;
  private final SpeakingGradeLevel learnerLevel;
  private final SpeakingGradeLanguage giverLanguage;
  private final SpeakingGradeLevel giverLevel;
  private final List<GoalResponse> goals;
  private final List<SubjectResponse> subjects;
  private final Boolean alarmStatus;
  private final SpeakingTestType testType;
  private final Integer followerCount;
  private final Integer followingCount;

  public MemberResponse(Long memberId, String nickname, String email, String profileImage,
      String introduce, MemberType memberType, SpeakingGradeLanguage learnerLanguage,
      SpeakingGradeLevel learnerLevel, SpeakingGradeLanguage giverLanguage,
      SpeakingGradeLevel giverLevel, List<GoalResponse> goals, List<SubjectResponse> subjects,
      Boolean alarmStatus, SpeakingTestType testType, Integer followerCount,
      Integer followingCount) {
    this.memberId = memberId;
    this.nickname = nickname;
    this.email = email;
    this.profileImage = profileImage;
    this.introduce = introduce;
    this.memberType = memberType;
    this.learnerLanguage = learnerLanguage;
    this.learnerLevel = learnerLevel;
    this.giverLanguage = giverLanguage;
    this.giverLevel = giverLevel;
    this.goals = goals;
    this.subjects = subjects;
    this.alarmStatus = alarmStatus;
    this.testType = testType;
    this.followerCount = followerCount;
    this.followingCount = followingCount;
  }
}
