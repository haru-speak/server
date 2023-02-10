package com.example.be.core.application;

import com.example.be.common.exception.member.NotFoundMemberIdException;
import com.example.be.common.exception.speakinglog.NotFoundSpeakingLogIdException;
import com.example.be.common.exception.speakinglog.NotMatchSpeakingLogAndMemberException;
import com.example.be.core.application.dto.request.SpeakingLogConditionRequest;
import com.example.be.core.application.dto.request.SpeakingLogModifyRequest;
import com.example.be.core.application.dto.request.SpeakingLogRequest;
import com.example.be.core.application.dto.response.CommentResponse;
import com.example.be.core.application.dto.response.SpeakingLogDetailResponse;
import com.example.be.core.application.dto.response.SpeakingLogResponse;
import com.example.be.core.application.dto.response.SpeakingLogsResponse;
import com.example.be.core.domain.member.Follow;
import com.example.be.core.domain.member.Member;
import com.example.be.core.domain.speakinglog.Favorite;
import com.example.be.core.domain.speakinglog.SpeakingLog;
import com.example.be.core.domain.speakinglog.SpeakingLogType;
import com.example.be.core.repository.member.FollowRepository;
import com.example.be.core.repository.member.MemberRepository;
import com.example.be.core.repository.speakinglog.CommentRepository;
import com.example.be.core.repository.speakinglog.FavoriteRepository;
import com.example.be.core.repository.speakinglog.SpeakingLogRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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
	private final FollowRepository followRepository;

	public SpeakingLogService(SpeakingLogRepository speakingLogRepository,
		FavoriteRepository favoriteRepository, CommentRepository commentRepository,
		MemberRepository memberRepository, FollowRepository followRepository) {
		this.speakingLogRepository = speakingLogRepository;
		this.favoriteRepository = favoriteRepository;
		this.commentRepository = commentRepository;
		this.memberRepository = memberRepository;
		this.followRepository = followRepository;
	}

	@Transactional
	public SpeakingLogDetailResponse create(Long memberId,
		SpeakingLogRequest speakingLogRequest) {
		log.debug("[스피킹 로그 생성] speakingLogRequest = {}", speakingLogRequest);
		Member member = memberRepository.findById(memberId)
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

	public SpeakingLogsResponse find(Long memberId, SpeakingLogConditionRequest speakingLogConditionRequest) {
		log.debug("[스피킹 로그 전체 조회] SpeakingLogConditionRequest = {}",speakingLogConditionRequest.toString());
		LocalDate date = speakingLogConditionRequest.getDate();
		LocalDateTime startDateTime = LocalDateTime.of(date.minusDays(1), LocalTime.of(0,0,0));
		LocalDateTime endDateTime = LocalDateTime.of(date, LocalTime.of(23,59,59));

		List<SpeakingLog> allSpeakingLogs = speakingLogRepository.findAllByCreatedAtBetween(startDateTime, endDateTime);
		SpeakingLogType type = speakingLogConditionRequest.getType();
		List<SpeakingLogResponse> speakingLogResponses;

		if (type == SpeakingLogType.MY) {
			speakingLogResponses = getSpeakingLogResponses(memberId, allSpeakingLogs);
		} else if (type == SpeakingLogType.MATE) {
			List<Follow> followings = followRepository.findAllByFollowerId(memberId);
			Set<Long> mates = followings.stream()
				.map(f -> f.getFollowing().getId())
				.collect(Collectors.toSet());
			speakingLogResponses = getSpeakingLogResponses(memberId, allSpeakingLogs, mates);
		} else {
			speakingLogResponses = allSpeakingLogs
				.stream()
				.map(s -> new SpeakingLogResponse(
						s.getId(),
						s.getTitle(),
						s.getVoiceRecord(),
						s.getVoiceText(),
						getFavoriteCount(s.getId()),
						getCommentCount(s.getId()),
						presentOfFavorite(memberId, s),
						s.getMember().getProfileImage(),
						s.getId()
					))
				.collect(Collectors.toList());
		}

		return new SpeakingLogsResponse(type, date, speakingLogResponses);
	}

	private boolean presentOfFavorite(Long memberId, SpeakingLog speakingLog) {
		return favoriteRepository
			.findByMemberIdAndSpeakingLog(memberId, speakingLog)
			.isPresent();
	}

	private List<SpeakingLogResponse> getSpeakingLogResponses(Long memberId, List<SpeakingLog> allSpeakingLogs, Set<Long> mates) {
		return allSpeakingLogs
			.stream()
			.filter(s -> mates.contains(s.getMember().getId()))
			.map(s -> new SpeakingLogResponse(
				s.getId(),
				s.getTitle(),
				s.getVoiceRecord(),
				s.getVoiceText(),
				getFavoriteCount(s.getId()),
				getCommentCount(s.getId()),
				presentOfFavorite(memberId, s),
				s.getMember().getProfileImage(),
				s.getMember().getId()
			))
			.collect(Collectors.toList());
	}

	private List<SpeakingLogResponse> getSpeakingLogResponses(Long memberId, List<SpeakingLog> allSpeakingLogs) {
		return allSpeakingLogs
			.stream()
			.filter(s -> s.getMember().getId().equals(memberId))
			.map(s -> new SpeakingLogResponse(
				s.getId(),
				s.getTitle(),
				s.getVoiceRecord(),
				s.getVoiceText(),
				getFavoriteCount(s.getId()),
				getCommentCount(s.getId()),
				presentOfFavorite(memberId, s),
				s.getMember().getProfileImage(),
				s.getMember().getId()
			))
			.collect(Collectors.toList());
	}


	public SpeakingLogDetailResponse findById(Long memberId, Long speakingLogId) {
		log.debug("[스피킹 로그 상세 조회] SpeakingLogId = {}", speakingLogId);

		SpeakingLog speakingLog = speakingLogRepository.findById(speakingLogId)
			.orElseThrow(NotFoundSpeakingLogIdException::new);

		Optional<Favorite> favoriteOfLoginMember = favoriteRepository.findByMemberIdAndSpeakingLog(memberId, speakingLog);

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
	public SpeakingLogDetailResponse modify(Long memberId, Long speakingLogId, SpeakingLogModifyRequest speakingLogModifyRequest) {
		log.debug("[스피킹 로그 수정] SpeakingLogId = {}, speakingLogRequest = {}", speakingLogId, speakingLogModifyRequest);

		SpeakingLog speakingLog = speakingLogRepository.findById(speakingLogId)
			.orElseThrow(NotFoundSpeakingLogIdException::new);

		speakingLog.modify(
			speakingLogModifyRequest.getTitle(),
			speakingLogModifyRequest.getVoiceRecord(),
			speakingLogModifyRequest.getVoiceText());

		Optional<Favorite> favoriteOfLoginMember = favoriteRepository.findByMemberIdAndSpeakingLog(memberId, speakingLog);

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
	public void delete(Long memberId, Long speakingLogId) {
		log.debug("[스피킹 로그 삭제] SpeakingLogId = {}", speakingLogId);

		SpeakingLog speakingLog = speakingLogRepository.findById(speakingLogId)
			.orElseThrow(NotFoundSpeakingLogIdException::new);

		if (!Objects.equals(speakingLog.getMember().getId(), memberId)) {
			throw new NotMatchSpeakingLogAndMemberException();
		}
		speakingLogRepository.deleteById(speakingLogId);

	}
}
