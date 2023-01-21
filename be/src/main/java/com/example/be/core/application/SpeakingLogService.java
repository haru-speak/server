package com.example.be.core.application;

import com.example.be.common.exception.speakinglog.NotFoundMemberIdException;
import com.example.be.common.exception.speakinglog.NotFoundSpeakingLogIdException;
import com.example.be.core.application.dto.request.SpeakingLogConditionRequest;
import com.example.be.core.application.dto.request.SpeakingLogModifyRequest;
import com.example.be.core.application.dto.request.SpeakingLogRequest;
import com.example.be.core.application.dto.response.CommentResponse;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import com.example.be.core.application.dto.response.SpeakingLogsResponse;
import com.example.be.core.domain.member.Member;
import com.example.be.core.domain.speakinglog.Favorite;
import com.example.be.core.domain.speakinglog.SpeakingLog;
import com.example.be.core.repository.member.MemberRespository;
import com.example.be.core.repository.speakinglog.CommentRepository;
import com.example.be.core.repository.speakinglog.FavoriteRepository;
import com.example.be.core.repository.speakinglog.SpeakingLogRepository;
import java.util.ArrayList;
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

	private static final Integer ZERO = 0;
	private final SpeakingLogRepository speakingLogRepository;
	private final FavoriteRepository favoriteRepository;
	private final CommentRepository commentRepository;
	private final MemberRespository memberRespository;

	public SpeakingLogService(SpeakingLogRepository speakingLogRepository,
		FavoriteRepository favoriteRepository, CommentRepository commentRepository,
		MemberRespository memberRespository) {
		this.speakingLogRepository = speakingLogRepository;
		this.favoriteRepository = favoriteRepository;
		this.commentRepository = commentRepository;
		this.memberRespository = memberRespository;
	}

	@Transactional
	public SpeakingLogDetailResponse create(SpeakingLogRequest speakingLogRequest, Long loginMemberId) {
		log.debug("[스피킹 로그 생성] speakingLogRequest = {}", speakingLogRequest);
		Member member = memberRespository.findById(loginMemberId)
			.orElseThrow(NotFoundMemberIdException::new);

		SpeakingLog savedSpeakingLog = speakingLogRepository.save(
			new SpeakingLog(
				member,
				speakingLogRequest.getTitle(),
				speakingLogRequest.getVoiceRecord(),
				speakingLogRequest.getVoiceText()
			)
		);

		log.debug("[스피킹 로그 생성] 성공 : SpeakingLog ID={}", savedSpeakingLog.getId());
		return new SpeakingLogDetailResponse(
			savedSpeakingLog.getMember().getId(),
			savedSpeakingLog.getTitle(),
			savedSpeakingLog.getVoiceRecord(),
			savedSpeakingLog.getVoiceText(),
			ZERO,
			Boolean.FALSE,
			new ArrayList<>()
		);
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
