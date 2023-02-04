package com.example.be.core.repository.member.grade;

import com.example.be.core.domain.member.grade.SpeakingGrade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakingGradeRepository extends JpaRepository<SpeakingGrade, Long> {

	List<SpeakingGrade> findAllByMemberId(Long memberId);
}
