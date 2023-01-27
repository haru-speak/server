package com.example.be.core.domain.assignment;

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

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Assignment {

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
}
