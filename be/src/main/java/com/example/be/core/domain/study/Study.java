package com.example.be.core.domain.study;

import com.example.be.core.domain.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Entity
@Where(clause = "deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE speaking_log SET deleted = true WHERE speaking_log_id = ?")
public class Study extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;

    private String title;

    private String content;

    @Lob
    private String posterImage;

    private String language;

    private Integer level;

    private Integer timePerWeek;

    private String rule;

    private Integer capacity;

    private String goal;

    private String certificate;

    public Study(String title, String content, String posterImage, String language, Integer level, Integer timePerWeek, String rule, Integer capacity, String goal, String certificate) {
        this.title = title;
        this.content = content;
        this.posterImage = posterImage;
        this.language = language;
        this.level = level;
        this.timePerWeek = timePerWeek;
        this.rule = rule;
        this.capacity = capacity;
        this.goal = goal;
        this.certificate = certificate;
    }
}
