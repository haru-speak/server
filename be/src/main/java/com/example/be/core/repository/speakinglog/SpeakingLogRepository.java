package com.example.be.core.repository.speakinglog;

import com.example.be.core.domain.speakinglog.SpeakingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakingLogRepository extends JpaRepository<SpeakingLog, Long> {

}
