package com.example.be.core.application;

import com.example.be.common.exception.member.NotFoundMemberIdException;
import com.example.be.common.exception.member.goal.NotFoundGoalIdException;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

		SpeakingGrade learnerInfo = saveSpeakingGrade(
			SpeakingGradeType.LEARNER, memberSignUpRequest, member);

		SpeakingGrade giverInfo = saveSpeakingGrade(
			SpeakingGradeType.GIVER, memberSignUpRequest, member);

		return new MemberSignUpResponse(
			member.getId(),
			member.getMemberType(),
			learnerInfo.getLanguage(),
			learnerInfo.getLevel(),
			giverInfo.getLanguage(),
			giverInfo.getLevel(),
			getGoalResponses(memberSignUpRequest, member),
			getSubjectResponses(memberSignUpRequest, member),
			member.getAlarmStatus(),
			member.getTestType()
		);
	}

	private boolean hasNotSpeakingTestGoal(Set<Long> goals) {
		return !goals.contains(SPEAKING_TEST_GOAL_ID);
	}

	private List<GoalResponse> getGoalResponses(MemberSignUpRequest memberSignUpRequest, Member member) {
		return saveGoalMembers(memberSignUpRequest, member)
			.stream().map(GoalMember::getGoal)
			.map(GoalResponse::of)
			.collect(Collectors.toList());
	}

	private List<SubjectResponse> getSubjectResponses(MemberSignUpRequest memberSignUpRequest,
		Member member) {
		return saveSubjectMembers(memberSignUpRequest, member)
			.stream().map(SubjectMember::getSubject)
			.map(SubjectResponse::of)
			.collect(Collectors.toList());
	}

	private List<SubjectMember> saveSubjectMembers(MemberSignUpRequest memberSignUpRequest, Member member) {
		return memberSignUpRequest.getSubjects()
			.stream()
			.map(subjectId -> subjectMemberRepository.save(
				new SubjectMember(subjectRepository.findById(subjectId)
					.orElseThrow(NotFoundSubjectIdException::new), member)))
			.collect(Collectors.toList());
	}

	private List<GoalMember> saveGoalMembers(MemberSignUpRequest memberSignUpRequest, Member member) {
		return memberSignUpRequest.getGoals()
			.stream()
			.map(goalId -> goalMemberRepository.save
				(new GoalMember(goalRepository.findById(goalId)
					.orElseThrow(NotFoundGoalIdException::new), member)))
			.collect(Collectors.toList());
	}

	private SpeakingGrade saveSpeakingGrade(SpeakingGradeType type, MemberSignUpRequest request, Member member) {
		if (type == SpeakingGradeType.LEARNER) {
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
		return null;
	}
}
