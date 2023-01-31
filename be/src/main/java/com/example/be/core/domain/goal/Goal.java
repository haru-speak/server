package com.example.be.core.domain.goal;

import com.example.be.core.domain.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goal {

    @Id
    @Column(name = "goal_id")
    private Long id;

    private String content;

    public Goal(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
