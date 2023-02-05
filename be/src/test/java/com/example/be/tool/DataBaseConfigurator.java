package com.example.be.tool;

import com.example.be.common.exception.member.NotFoundMemberIdException;
import com.example.be.core.domain.assignment.Assignment;
import com.example.be.core.domain.assignment.AssignmentMember;
import com.example.be.core.domain.member.Member;
import com.example.be.core.domain.member.goal.Goal;
import com.example.be.core.domain.member.subject.Subject;
import com.example.be.core.domain.speakinglog.SpeakingLog;
import com.example.be.core.domain.study.Study;
import com.example.be.core.domain.study.StudyDay;
import com.example.be.core.domain.study.StudyMember;
import com.example.be.core.repository.assignment.AssignmentMemberRepository;
import com.example.be.core.repository.assignment.AssignmentRepository;
import com.example.be.core.repository.member.MemberRepository;
import com.example.be.core.repository.member.goal.GoalRepository;
import com.example.be.core.repository.member.subject.SubjectRepository;
import com.example.be.core.repository.speakinglog.SpeakingLogRepository;
import com.example.be.core.repository.study.StudyMemberRepository;
import com.example.be.core.repository.study.StudyRepository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataBaseConfigurator implements InitializingBean {

	private static final String SET_FOREIGN_KEY_CHECKS = "SET FOREIGN_KEY_CHECKS = ";
	private static final String TRUNCATE_TABLE = "TRUNCATE TABLE ";
	private static final String TABLE = "TABLE";
	private static final String TABLE_NAME = "table_name";

	private static final int NUMBER_OF_MEMBER = 5;
	private static final int NUMBER_OF_SPEAKING_LOG = 3;
	private static final int NUMBER_OF_STUDY = 4;
	private static final int NUMBER_OF_ASSIGNMENT = 3;
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private SpeakingLogRepository speakingLogRepository;

	@Autowired
	private StudyRepository studyRepository;

	@Autowired
	private StudyMemberRepository studyMemberRepository;

	@Autowired
	private AssignmentRepository assignmentRepository;

	@Autowired
	private AssignmentMemberRepository assignmentMemberRepository;

	@Autowired
	private GoalRepository goalRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@PersistenceContext
	private EntityManager entityManager;

	private List<String> tableNames;

	@Override
	public void afterPropertiesSet() throws Exception {
		entityManager.unwrap(Session.class).doWork(this::extractTableNames);
	}

	private void extractTableNames(Connection connection) throws SQLException {

		List<String> tableNames = new ArrayList<>();
		ResultSet tables = connection.getMetaData()
			.getTables(connection.getCatalog(), null, null, new String[]{TABLE});

		try (tables) {
			while (tables.next()) {
				tableNames.add(tables.getString(TABLE_NAME));
			}

			this.tableNames = tableNames;
		}
	}

	public void clear() {
		entityManager.unwrap(Session.class).doWork(this::cleanUpDataBase);
	}

	private void cleanUpDataBase(Connection connection) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(SET_FOREIGN_KEY_CHECKS + "0");
			for (String tableName : tableNames) {
				statement.executeUpdate(TRUNCATE_TABLE + tableName);
			}
			statement.executeUpdate(SET_FOREIGN_KEY_CHECKS + "1");
		}
	}

	public void initDataSource() {
		initMember();
		initSpeakingLog();
		initStudy();
		initAssignment();
		initGoal();
		initSubject();
	}

	/**
	 * Member 도메인 객체를 NUMBER_OF_MEMBER 만큼 생성합니다.
	 */
	private void initMember() {
		for (int i = 1; i <= NUMBER_OF_MEMBER; i++) {
			memberRepository.save(
				new Member(
					"member" + i,
					"member-email" + i + "@google.com",
					"uniqueId1234" + i,
					"https://s3.profile_img" + i + ".png"
				));
		}
	}

	/**
	 * SpeakingLog 도메인 객체를 각 멤버 마다 NUMBER_OF_SPEAKING_LOG 만큼 생성합니다.
	 */
	private void initSpeakingLog() {
		for (int i = 1; i <= NUMBER_OF_MEMBER; i++) {
			Member member = memberRepository.findById((long) i)
				.orElseThrow(NotFoundMemberIdException::new);

			for (int j = 1; j <= NUMBER_OF_SPEAKING_LOG; j++) {
				speakingLogRepository.save(
					new SpeakingLog(
						member,
						"speaking-log-title" + i + j,
						"https://dummy-voice-record" + i + j,
						"dummy-voice-text" + i + j
					));
			}
		}
	}

	/**
	 * Study 도메인 객체를 NUMBER_OF_STUDY 만큼 생성합니다.
	 */
	private void initStudy() {
		for (int i = 1; i <= NUMBER_OF_STUDY; i++) {
			Study study = studyRepository.save(
				new Study(
					"study-title" + i,
					"study-content" + i,
					"study-posterImage" + i,
					"study-language" + i,
					i,
					EnumSet.of(StudyDay.MONDAY),
					"study-rule" + i,
					"study-region" + i,
					i,
					i,
					"study-speakingTest" + i,
					"study-grade" + i
				));

			for (int j = 1; j <= NUMBER_OF_MEMBER; j++) {
				Member member = memberRepository.findById((long) j)
					.orElseThrow(NotFoundMemberIdException::new);
				studyMemberRepository.save(
					new StudyMember(
						member,
						study,
						Boolean.TRUE
					)
				);
			}
		}
	}

	/**
	 * Assignment 도메인 객체를 NUMBER_OF_ASSIGNMENT 만큼 생성합니다.
	 */
	private void initAssignment() {
		Study study = studyRepository.findById(1L).get();
		List<StudyMember> studyMembers = studyMemberRepository.findStudyMembersByStudyId(
			study.getId());
		for (int i = 1; i <= NUMBER_OF_ASSIGNMENT; i++) {
			Assignment assignment = assignmentRepository.save(
				new Assignment(
					study,
					"assignment-title" + i,
					LocalDateTime.now(),
					"assignment-content" + i,
					"https://dummy-voice-record" + i,
            "photo" + i )
			);
			studyMembers.forEach(it ->
				assignmentMemberRepository.save(
					new AssignmentMember (
						it.getMember(),
							assignment,
						"미제출")
				)
			);
		}
	}

	/**
	 * [고정 데이터]
	 * GOAL(목표)를 생성합니다.
	 */

	private void initGoal() {
		goalRepository.save(new Goal(1L, "일상 속 유용한 표현 배우기!"));
		goalRepository.save(new Goal(2L, "다른 사람들의 피드백!"));
		goalRepository.save(new Goal(3L, "듣기 능력 키우기!"));
		goalRepository.save(new Goal(4L, "함께 공부할 스터디 찾기!"));
		goalRepository.save(new Goal(5L, "어학 자격증 따기!"));
	}

	/**
	 * [고정 데이터] SUBJECT(주제)를 생성합니다.
	 */

	private void initSubject() {
		subjectRepository.save(new Subject(1L, "여행"));
		subjectRepository.save(new Subject(2L, "영화&음악"));
		subjectRepository.save(new Subject(3L, "일&회사"));
		subjectRepository.save(new Subject(4L, "쇼핑"));
		subjectRepository.save(new Subject(5L, "음식"));
		subjectRepository.save(new Subject(6L, "가족&친구"));
		subjectRepository.save(new Subject(7L, "운동&건강"));
		subjectRepository.save(new Subject(8L, "동네"));
		subjectRepository.save(new Subject(9L, "연애"));
	}
}
