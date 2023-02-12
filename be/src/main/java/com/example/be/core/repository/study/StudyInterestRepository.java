package com.example.be.core.repository.study;

import com.example.be.core.domain.study.Study;
import com.example.be.core.domain.study.StudyInterest;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyInterestRepository extends JpaRepository<StudyInterest, Long> {

  Integer countByStudyId(Long studyId);

  Optional<StudyInterest> findByMemberIdAndStudy(Long memberId, Study study);

  void deleteByMemberIdAndStudyId(Long memberId, Long studyId);
}
