package com.example.be.core.application;

import com.example.be.core.application.dto.request.SpeakingLogRequest;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import com.example.be.core.application.dto.response.SpeakingLogsResponse;
import com.example.be.core.domain.SpeakingLogType;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@Service
public class SpeakingLogService {

	public SpeakingLogsResponse find(SpeakingLogType type, LocalDate date) {
		log.debug("SpeakingLogType = {}, Date = {}", type, date);
		return null;
	}

	public SpeakingLogDetailResponse findById(Long speakingLogId) {
		log.debug("SpeakingLogId = {}", speakingLogId);
		return null;
	}

	public void delete(Long speakingLogId) {
		log.debug("SpeakingLogId = {}", speakingLogId);
	}

	public void modify(Long speakingLogId, SpeakingLogRequest speakingLogRequest) {
		log.debug("SpeakingLogId = {}, speakingLogRequest = {}", speakingLogId, speakingLogRequest);
	}

	public SpeakingLogDetailResponse create(SpeakingLogRequest speakingLogRequest) {
		log.debug("speakingLogRequest = {}", speakingLogRequest);
		return null;
	}
}
