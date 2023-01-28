package com.example.be.core.domain.question;

import com.example.be.core.domain.member.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionSubject {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "subject_question_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "subject_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private Question question;

  public QuestionSubject(Member member, Question question) {
    this.member = member;
    this.question = question;
  }
}
