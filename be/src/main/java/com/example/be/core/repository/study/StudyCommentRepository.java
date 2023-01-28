package com.example.be.core.repository.study;

import com.example.be.core.domain.study.StudyComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyCommentRepository extends JpaRepository<StudyComment, Long> {

  Integer countByStudyId(Long studyId);
}
