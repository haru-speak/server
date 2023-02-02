package com.example.be.core.domain.member.goal;

import javax.persistence.Column;
import javax.persistence.Entity;
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
}
