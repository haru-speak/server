package com.example.be.core.application;

import com.example.be.common.exception.speakinglog.NotFoundSpeakingLogIdException;
import com.example.be.core.application.dto.request.SpeakingLogConditionRequest;
import com.example.be.core.application.dto.request.SpeakingLogModifyRequest;
import com.example.be.core.application.dto.request.SpeakingLogRequest;
import com.example.be.core.application.dto.response.CommentResponse;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import com.example.be.core.application.dto.response.SpeakingLogsResponse;
import com.example.be.core.domain.speakinglog.Favorite;
import com.example.be.core.domain.speakinglog.SpeakingLog;
import com.example.be.core.repository.speakinglog.CommentRepository;
import com.example.be.core.repository.speakinglog.FavoriteRepository;
import com.example.be.core.repository.speakinglog.SpeakingLogRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class SpeakingLogService {

	private final SpeakingLogRepository speakingLogRepository;
	private final FavoriteRepository favoriteRepository;
	private final CommentRepository commentRepository;

	public SpeakingLogService(SpeakingLogRepository speakingLogRepository,
		FavoriteRepository favoriteRepository, CommentRepository commentRepository) {
		this.speakingLogRepository = speakingLogRepository;
		this.favoriteRepository = favoriteRepository;
		this.commentRepository = commentRepository;
	}

	@Transactional
	public SpeakingLogDetailResponse create(SpeakingLogRequest speakingLogRequest) {
		log.debug("speakingLogRequest = {}", speakingLogRequest);
		return null;
	}

	public SpeakingLogsResponse find(SpeakingLogConditionRequest speakingLogConditionRequest) {
		log.debug(speakingLogConditionRequest.toString());
		return null;
	}

	public SpeakingLogDetailResponse findById(Long speakingLogId, Long loginMemberId) {
		log.debug("[스피킹 로그 상세 조회] SpeakingLogId = {}", speakingLogId);

		SpeakingLog speakingLog = speakingLogRepository.findById(speakingLogId)
			.orElseThrow(NotFoundSpeakingLogIdException::new);

		// 임시 (아직 로그인 구현 X)

		Optional<Favorite> favoriteOfLoginMember = favoriteRepository.findByMemberIdAndSpeakingLog(loginMemberId, speakingLog);

		return new SpeakingLogDetailResponse(
			speakingLog.getMember().getId(),
			speakingLog.getTitle(),
			speakingLog.getVoiceRecord(),
			speakingLog.getVoiceText(),
			favoriteRepository.countBySpeakingLogId(speakingLogId),
			favoriteOfLoginMember.isPresent(),
			getCommentResponses(speakingLogId)
		);
	}

	private List<CommentResponse> getCommentResponses(Long speakingLogId) {
		return commentRepository.findAllBySpeakingLogId(speakingLogId)
			.stream()
			.map(CommentResponse::from)
			.collect(Collectors.toList());
	}

	@Transactional
	public SpeakingLogDetailResponse modify(Long speakingLogId, SpeakingLogModifyRequest speakingLogModifyRequest) {
		log.debug("SpeakingLogId = {}, speakingLogRequest = {}", speakingLogId, speakingLogModifyRequest);
		return null;
	}

	@Transactional
	public void delete(Long speakingLogId) {
		log.debug("SpeakingLogId = {}", speakingLogId);
	}
}
