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
@SQLDelete(sql = "UPDATE study SET deleted = true WHERE study_id = ?")
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

    private String timePerWeek;

    private String rule;

    private String region;

    private Integer maxCapacity;

    private Integer minCapacity;

    private String speakingTest;

    private String grade;

    public Study(String title, String content, String posterImage, String language, Integer level,
        String timePerWeek, String rule, String region, Integer maxCapacity, Integer minCapacity,
        String speakingTest, String grade) {
        this.title = title;
        this.content = content;
        this.posterImage = posterImage;
        this.language = language;
        this.level = level;
        this.timePerWeek = timePerWeek;
        this.rule = rule;
        this.region = region;
        this.maxCapacity = maxCapacity;
        this.minCapacity = minCapacity;
        this.speakingTest = speakingTest;
        this.grade = grade;
    }

    public void modify(String title, String content, String posterImage, String language, Integer level,
        String timePerWeek, String rule, String region, Integer maxCapacity, Integer minCapacity,
        String speakingTest, String grade) {
        this.title = title;
        this.content = content;
        this.posterImage = posterImage;
        this.language = language;
        this.level = level;
        this.timePerWeek = timePerWeek;
        this.rule = rule;
        this.region = region;
        this.maxCapacity = maxCapacity;
        this.minCapacity = minCapacity;
        this.speakingTest = speakingTest;
        this.grade = grade;
    }
}
