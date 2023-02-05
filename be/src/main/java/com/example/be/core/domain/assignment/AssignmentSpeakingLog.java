package com.example.be.core.domain.assignment;

import com.example.be.core.domain.BaseEntity;
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
@SQLDelete(sql = "UPDATE assignment_speaking_log SET deleted = true WHERE assignment_speaking_log_id = ?")
public class AssignmentSpeakingLog extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "assignment_speaking_log_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "assignment_member_id", nullable = false)
  private AssignmentMember assignmentMember;

  private String title;

  @Lob
  private String voiceRecord;

  @Lob
  private String voiceText;

  public AssignmentSpeakingLog(AssignmentMember assignmentMember, String title, String voiceRecord, String voiceText) {
    this.assignmentMember = assignmentMember;
    this.title = title;
    this.voiceRecord = voiceRecord;
    this.voiceText = voiceText;
  }

  public void modify(String title, String voiceRecord, String voiceText) {
    this.title = title;
    this.voiceRecord = voiceRecord;
    this.voiceText = voiceText;
  }
}
