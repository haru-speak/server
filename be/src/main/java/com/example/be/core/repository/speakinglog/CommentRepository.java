package com.example.be.core.repository.speakinglog;

import com.example.be.core.domain.speakinglog.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	Integer countBySpeakingLogId(Long speakingLogId);

	List<Comment> findAllBySpeakingLogId(Long speakingLogId);
}
