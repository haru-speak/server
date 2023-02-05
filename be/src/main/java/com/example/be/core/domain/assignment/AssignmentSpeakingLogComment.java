package com.example.be.core.domain.assignment;

import com.example.be.core.domain.BaseEntity;
import com.example.be.core.domain.member.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Entity
@Where(clause = "deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE assignment_speaking_log SET deleted = true WHERE assignment_speaking_log_comment_id = ?")
public class AssignmentSpeakingLogComment extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "assignment_speaking_log_comment_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "assignment_speaking_log_id")
  private AssignmentSpeakingLog assignmentSpeakingLog;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @Lob
  private String content;

  public AssignmentSpeakingLogComment(AssignmentSpeakingLog assignmentSpeakingLog, Member member, String content) {
    this.assignmentSpeakingLog = assignmentSpeakingLog;
    this.member = member;
    this.content = content;
  }
}
