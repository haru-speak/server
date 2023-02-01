package com.example.be.core.repository.speakinglog;

import com.example.be.core.domain.speakinglog.SpeakingLog;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SpeakingLogRepository extends JpaRepository<SpeakingLog, Long> {

    List<SpeakingLog> findAllByCreatedAtBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
