package com.example.be.core.domain.assignment;

import com.example.be.core.domain.BaseEntity;
import com.example.be.core.domain.study.Study;
import java.time.LocalDateTime;
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
@SQLDelete(sql = "UPDATE assignment SET deleted = true WHERE assignment_id = ?")
public class Assignment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    private String title;

    private LocalDateTime deadLine;

    private String content;

    @Lob
    private String voiceRecord;

    public Assignment(Study study, String title, LocalDateTime deadLine, String content,
        String voiceRecord) {
        this.study = study;
        this.title = title;
        this.deadLine = deadLine;
        this.content = content;
        this.voiceRecord = voiceRecord;
    }

    public void modify (String title, LocalDateTime deadLine, String content,
        String voiceRecord) {
        this.title = title;
        this.deadLine = deadLine;
        this.content = content;
        this.voiceRecord = voiceRecord;
    }
}
