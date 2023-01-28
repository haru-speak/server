package com.example.be.core.domain.question;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "question_id")
  private Long id;

  @Lob
  private String voiceRecord;

  @Lob
  private String voiceText;

  public Question(Long id, String voiceRecord, String voiceText) {
    this.id = id;
    this.voiceRecord = voiceRecord;
    this.voiceText = voiceText;
  }
}
