package com.example.be.core.application;

import com.example.be.common.exception.speakinglog.NotFoundMemberIdException;
import com.example.be.common.exception.speakinglog.NotFoundSpeakingLogIdException;
import com.example.be.core.application.dto.request.SpeakingLogConditionRequest;
import com.example.be.core.application.dto.request.SpeakingLogModifyRequest;
import com.example.be.core.application.dto.request.SpeakingLogRequest;
import com.example.be.core.application.dto.response.CommentResponse;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import com.example.be.core.application.dto.response.SpeakingLogResponse;
import com.example.be.core.application.dto.response.SpeakingLogsResponse;
import com.example.be.core.domain.member.Member;
import com.example.be.core.domain.speakinglog.Favorite;
import com.example.be.core.domain.speakinglog.SpeakingLog;
import com.example.be.core.repository.member.MemberRepository;
import com.example.be.core.repository.speakinglog.CommentRepository;
import com.example.be.core.repository.speakinglog.FavoriteRepository;
import com.example.be.core.repository.speakinglog.SpeakingLogRepository;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
	private final MemberRepository memberRepository;

	public SpeakingLogService(SpeakingLogRepository speakingLogRepository,
		FavoriteRepository favoriteRepository, CommentRepository commentRepository,
		MemberRepository memberRepository) {
		this.speakingLogRepository = speakingLogRepository;
		this.favoriteRepository = favoriteRepository;
		this.commentRepository = commentRepository;
		this.memberRepository = memberRepository;
	}

	@Transactional
	public SpeakingLogDetailResponse create(SpeakingLogRequest speakingLogRequest, Long loginMemberId) {
		log.debug("[스피킹 로그 생성] speakingLogRequest = {}", speakingLogRequest);
		Member member = memberRepository.findById(loginMemberId)
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
		log.debug("[스피킹 로그 전체 조회] SpeakingLogConditionRequest = {}",speakingLogConditionRequest.toString());

		LocalDateTime startDateTime = LocalDateTime.of(speakingLogConditionRequest.getDate().minusDays(1),
			LocalTime.of(0,0,0));

		LocalDateTime endDateTime = LocalDateTime.of(speakingLogConditionRequest.getDate(), LocalTime.of(23,59,59));

		List<SpeakingLog> speakingLogs = speakingLogRepository.findAllByCreatedAtBetween(
			startDateTime, endDateTime);

		// 임시 (아직 로그인 구현 X)
		Long loginMemberId = 1L;

		List<SpeakingLogResponse> speakingLogResponses = speakingLogs.stream().map(speakingLog ->
			new SpeakingLogResponse(
				speakingLog.getId(),
				speakingLog.getTitle(),
				speakingLog.getVoiceRecord(),
				getFavoriteCount(speakingLog.getId()),
				getCommentCount(speakingLog.getId()),
				favoriteRepository.findByMemberIdAndSpeakingLog(loginMemberId, speakingLog).isPresent(),
				speakingLog.getMember().getProfileImage(),
				speakingLog.getId()
			)).collect(Collectors.toList());

		return new SpeakingLogsResponse(
			speakingLogConditionRequest.getType(),
			speakingLogConditionRequest.getDate(),
			speakingLogResponses
		);
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
			getFavoriteCount(speakingLogId),
			favoriteOfLoginMember.isPresent(),
			getCommentResponses(speakingLogId)
		);
	}

	private Integer getFavoriteCount(Long speakingLogId) {
		return favoriteRepository.countBySpeakingLogId(speakingLogId);
	}

	private Integer getCommentCount(Long speakingLogId) {
		return commentRepository.countBySpeakingLogId(speakingLogId);
	}

	private List<CommentResponse> getCommentResponses(Long speakingLogId) {
		return commentRepository.findAllBySpeakingLogId(speakingLogId)
			.stream()
			.map(CommentResponse::from)
			.collect(Collectors.toList());
	}


	@Transactional
	public SpeakingLogDetailResponse modify(Long speakingLogId, SpeakingLogModifyRequest speakingLogModifyRequest) {
		log.debug("[스피킹 로그 수정] SpeakingLogId = {}, speakingLogRequest = {}", speakingLogId, speakingLogModifyRequest);

		SpeakingLog speakingLog = speakingLogRepository.findById(speakingLogId)
			.orElseThrow(NotFoundSpeakingLogIdException::new);

		speakingLog.modify(
			speakingLogModifyRequest.getTitle(),
			speakingLogModifyRequest.getVoiceRecord(),
			speakingLogModifyRequest.getVoiceText());

		//임시 로그인 아이디
		Optional<Favorite> favoriteOfLoginMember = favoriteRepository.findByMemberIdAndSpeakingLog(1L, speakingLog);

		return new SpeakingLogDetailResponse(
			speakingLog.getMember().getId(),
			speakingLog.getTitle(),
			speakingLog.getVoiceRecord(),
			speakingLog.getVoiceText(),
			getFavoriteCount(speakingLogId),
			favoriteOfLoginMember.isPresent(),
			getCommentResponses(speakingLogId)
		);
	}

	@Transactional
	public void delete(Long speakingLogId) {
		log.debug("[스피킹 로그 삭제] SpeakingLogId = {}", speakingLogId);

		speakingLogRepository.deleteById(speakingLogId);
	}
}
