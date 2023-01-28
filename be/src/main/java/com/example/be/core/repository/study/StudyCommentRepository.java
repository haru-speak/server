package com.example.be.core.repository.study;

import com.example.be.core.domain.speakinglog.Comment;
import com.example.be.core.domain.study.StudyComment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyCommentRepository extends JpaRepository<StudyComment, Long> {

  Integer countByStudyId(Long studyId);

  List<StudyComment> findAllByStudyId(Long studyId);
}
