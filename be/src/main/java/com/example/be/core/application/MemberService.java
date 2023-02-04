package com.example.be.core.application;

import static com.example.be.core.domain.member.grade.SpeakingGradeType.GIVER;
import static com.example.be.core.domain.member.grade.SpeakingGradeType.LEARNER;

import com.example.be.common.exception.member.NotFoundMemberIdException;
import com.example.be.common.exception.member.goal.NotFoundGoalIdException;
import com.example.be.common.exception.member.grade.InvalidSizeSpeakingGrade;
import com.example.be.common.exception.member.subject.NotFoundSubjectIdException;
import com.example.be.core.application.dto.request.MemberModifyRequest;
import com.example.be.core.application.dto.request.MemberSignUpRequest;
import com.example.be.core.application.dto.response.GoalResponse;
import com.example.be.core.application.dto.response.MemberResponse;
import com.example.be.core.application.dto.response.MemberSignUpResponse;
import com.example.be.core.application.dto.response.SubjectResponse;
import com.example.be.core.domain.member.Member;
import com.example.be.core.domain.member.goal.GoalMember;
import com.example.be.core.domain.member.grade.SpeakingGrade;
import com.example.be.core.domain.member.grade.SpeakingGradeType;
import com.example.be.core.domain.member.subject.SubjectMember;
import com.example.be.core.repository.member.MemberRepository;
import com.example.be.core.repository.member.goal.GoalMemberRepository;
import com.example.be.core.repository.member.goal.GoalRepository;
import com.example.be.core.repository.member.grade.SpeakingGradeRepository;
import com.example.be.core.repository.member.subject.SubjectMemberRepository;
import com.example.be.core.repository.member.subject.SubjectRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class MemberService {

	private static final Long SPEAKING_TEST_GOAL_ID = 5L;

	private final MemberRepository memberRepository;
	private final SpeakingGradeRepository speakingGradeRepository;
	private final GoalRepository goalRepository;
	private final GoalMemberRepository goalMemberRepository;
	private final SubjectRepository subjectRepository;
	private final SubjectMemberRepository subjectMemberRepository;

	public MemberService(MemberRepository memberRepository,
		SpeakingGradeRepository speakingGradeRepository, GoalRepository goalRepository,
		GoalMemberRepository goalMemberRepository, SubjectRepository subjectRepository,
		SubjectMemberRepository subjectMemberRepository) {
		this.memberRepository = memberRepository;
		this.speakingGradeRepository = speakingGradeRepository;
		this.goalRepository = goalRepository;
		this.goalMemberRepository = goalMemberRepository;
		this.subjectRepository = subjectRepository;
		this.subjectMemberRepository = subjectMemberRepository;
	}

	@Transactional
	public MemberSignUpResponse signUp(Long memberId, MemberSignUpRequest memberSignUpRequest) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(NotFoundMemberIdException::new);


		if (hasNotSpeakingTestGoal(new HashSet<>(memberSignUpRequest.getGoals()))) {
			memberSignUpRequest.updateSpeakingTestType(null);
		}

		member.signUp(
			memberSignUpRequest.getMemberType(),
			memberSignUpRequest.getAlarmStatus(),
			memberSignUpRequest.getTestType()
		);

		SpeakingGrade learnerInfo = saveSpeakingGrade(LEARNER, memberSignUpRequest, member);
		SpeakingGrade giverInfo = saveSpeakingGrade(GIVER, memberSignUpRequest, member);

		return new MemberSignUpResponse(
			member.getId(),
			member.getMemberType(),
			learnerInfo.getLanguage(),
			learnerInfo.getLevel(),
			giverInfo.getLanguage(),
			giverInfo.getLevel(),
			getGoalResponses(saveGoalMembers(memberSignUpRequest, member)),
			getSubjectResponses(saveSubjectMembers(memberSignUpRequest, member)),
			member.getAlarmStatus(),
			member.getTestType()
		);
	}

	private boolean hasNotSpeakingTestGoal(Set<Long> goals) {
		return !goals.contains(SPEAKING_TEST_GOAL_ID);
	}

	private List<GoalResponse> getGoalResponses(List<GoalMember> goalMembers) {
		return goalMembers
			.stream().map(GoalMember::getGoal)
			.map(GoalResponse::of)
			.collect(Collectors.toList());
	}

	private List<SubjectResponse> getSubjectResponses(List<SubjectMember> subjectMembers) {
		return subjectMembers
			.stream().map(SubjectMember::getSubject)
			.map(SubjectResponse::of)
			.collect(Collectors.toList());
	}

	private List<SubjectMember> saveSubjectMembers(MemberSignUpRequest request, Member member) {
		return request.getSubjects()
			.stream()
			.map(subjectId -> subjectMemberRepository.save(
				new SubjectMember(subjectRepository.findById(subjectId)
					.orElseThrow(NotFoundSubjectIdException::new), member)))
			.collect(Collectors.toList());
	}

	private List<GoalMember> saveGoalMembers(MemberSignUpRequest request, Member member) {
		return request.getGoals()
			.stream()
			.map(goalId -> goalMemberRepository.save
				(new GoalMember(goalRepository.findById(goalId)
					.orElseThrow(NotFoundGoalIdException::new), member)))
			.collect(Collectors.toList());
	}

	private SpeakingGrade saveSpeakingGrade(SpeakingGradeType type, MemberSignUpRequest request, Member member) {
		if (type == LEARNER) {
			return speakingGradeRepository.save(
				new SpeakingGrade(type, request.getLearnerLanguage(),
					request.getLearnerLevel(), member));
		}
		return speakingGradeRepository.save(
			new SpeakingGrade(type, request.getGiverLanguage(),
				request.getGiverLevel(), member));
	}

	@Transactional
	public MemberResponse modify(Long memberId, MemberModifyRequest memberModifyRequest) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(NotFoundMemberIdException::new);

		member.modifyMyInformation(
			memberModifyRequest.getNickname(),
			memberModifyRequest.getEmail(),
			memberModifyRequest.getProfileImage(),
			memberModifyRequest.getIntroduce(),
			memberModifyRequest.getMemberType(),
			memberModifyRequest.getAlarmStatus(),
			memberModifyRequest.getTestType()
		);

		List<SpeakingGrade> learnerAndGiverInfo = modifyLearnerAndGiverSpeakingGrades(memberId, memberModifyRequest);

		return new MemberResponse(
			member.getId(),
			member.getNickname(),
			member.getEmail(),
			member.getProfileImage(),
			member.getIntroduce(),
			member.getMemberType(),
			learnerAndGiverInfo.get(0).getLanguage(),
			learnerAndGiverInfo.get(0).getLevel(),
			learnerAndGiverInfo.get(1).getLanguage(),
			learnerAndGiverInfo.get(1).getLevel(),
			getGoalResponses(modifyGoalMembers(memberModifyRequest, member)),
			getSubjectResponses(modifySubjectMembers(memberModifyRequest, member)),
			member.getAlarmStatus(),
			member.getTestType());
	}

	private List<SubjectMember> modifySubjectMembers(MemberModifyRequest memberModifyRequest, Member member) {
		subjectMemberRepository.deleteAllInBatchByMemberId(member.getId());
		return memberModifyRequest.getSubjects()
			.stream()
			.map(s -> subjectMemberRepository.save(
				new SubjectMember(subjectRepository.findById(s)
						.orElseThrow(NotFoundSubjectIdException::new), member)))
			.collect(Collectors.toList());
	}

	private List<GoalMember> modifyGoalMembers(MemberModifyRequest memberModifyRequest, Member member) {
		goalMemberRepository.deleteAllInBatchByMemberId(member.getId());
		return memberModifyRequest.getGoals()
			.stream()
			.map(g -> goalMemberRepository.save(
				new GoalMember(goalRepository.findById(g)
						.orElseThrow(NotFoundGoalIdException::new), member)))
			.collect(Collectors.toList());
	}

	private List<SpeakingGrade> modifyLearnerAndGiverSpeakingGrades(Long memberId, MemberModifyRequest memberModifyRequest) {
		List<SpeakingGrade> speakingGrades = speakingGradeRepository.findAllByMemberId(memberId);
		if (speakingGrades.size() != 2) {
			throw new InvalidSizeSpeakingGrade();
		}
		speakingGrades.sort(
			(o1, o2) -> Math.toIntExact(o1.getId() - o2.getId())
		);
		SpeakingGrade learnerInfo = speakingGrades.get(0);
		SpeakingGrade giverInfo = speakingGrades.get(1);
		log.debug("[BEFORE MODIFY] 1st SpeakingGrade = {}, 2nd SpeakingGrade = {}", learnerInfo, giverInfo);
		learnerInfo.modify(
			memberModifyRequest.getLearnerLanguage(),
			memberModifyRequest.getLearnerLevel());
		giverInfo.modify(
			memberModifyRequest.getGiverLanguage(),
			memberModifyRequest.getGiverLevel());
		log.debug("[AFTER MODIFY] 1st SpeakingGrade = {}, 2nd SpeakingGrade = {}", learnerInfo, giverInfo);
		return Arrays.asList(learnerInfo, giverInfo);
	}
}
