package com.example.be.core.repository.study;

import com.example.be.core.domain.study.Study;
import com.example.be.core.domain.study.StudyFavorite;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyFavoriteRepository extends JpaRepository<StudyFavorite, Long> {

  Integer countByStudyId(Long studyId);

  Optional<StudyFavorite> findByMemberIdAndStudy(Long memberId, Study study);
}
