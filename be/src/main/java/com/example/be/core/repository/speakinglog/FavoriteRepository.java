package com.example.be.core.repository.speakinglog;

import com.example.be.core.domain.speakinglog.Favorite;
import com.example.be.core.domain.speakinglog.SpeakingLog;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

	Integer countBySpeakingLogId(Long speakingLogId);

	Optional<Favorite> findByMemberIdAndSpeakingLog(Long memberId, SpeakingLog speakingLog);
}
