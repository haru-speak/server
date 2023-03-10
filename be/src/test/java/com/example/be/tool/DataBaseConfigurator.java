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
import com.example.be.core.domain.study.StudyInterest;
import com.example.be.core.domain.study.StudyMember;
import com.example.be.core.domain.study.StudyRegion;
import com.example.be.core.repository.assignment.AssignmentMemberRepository;
import com.example.be.core.repository.assignment.AssignmentRepository;
import com.example.be.core.repository.member.MemberRepository;
import com.example.be.core.repository.member.goal.GoalRepository;
import com.example.be.core.repository.member.subject.SubjectRepository;
import com.example.be.core.repository.speakinglog.SpeakingLogRepository;
import com.example.be.core.repository.study.StudyInterestRepository;
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
	private static final String staticImageUrl = "https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/static/";
	private static final String staticImageExt = ".png";

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
	private StudyInterestRepository studyInterestRepository;

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

	EnumSet<StudyDay> studyDay = EnumSet.allOf(StudyDay.class);

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
	 * Member ????????? ????????? NUMBER_OF_MEMBER ?????? ???????????????.
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
	 * SpeakingLog ????????? ????????? ??? ?????? ?????? NUMBER_OF_SPEAKING_LOG ?????? ???????????????.
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
	 * Study ????????? ????????? NUMBER_OF_STUDY ?????? ???????????????.
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
					studyDay,
					"study-rule" + i,
					StudyRegion.SEOUL,
					i,
					i,
					"study-speakingTest" + i,
					"study-grade" + i
				));

			for (int j = 1; j <= NUMBER_OF_MEMBER; j++) {
				Member member = memberRepository.findById((long) j)
					.orElseThrow(NotFoundMemberIdException::new);
				boolean leader = Boolean.FALSE;
				if (j == 1) {
					leader = Boolean.TRUE;
				}
				studyMemberRepository.save(
					new StudyMember(
						member,
						study,
						leader
					)
				);
				if (i == 2 && j == 1) {
					studyInterestRepository.save(
						new StudyInterest(
							member,
							study
						)
					);
				}
			}
		}
	}

	/**
	 * Assignment ????????? ????????? NUMBER_OF_ASSIGNMENT ?????? ???????????????.
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
						"?????????")
				)
			);
		}
	}

	/**
	 * [?????? ?????????]
	 * GOAL(??????)??? ???????????????.
	 */

	private void initGoal() {
		goalRepository.save(new Goal(1L, "?????? ??? ????????? ?????? ?????????!"));
		goalRepository.save(new Goal(2L, "?????? ???????????? ?????????!"));
		goalRepository.save(new Goal(3L, "?????? ?????? ?????????!"));
		goalRepository.save(new Goal(4L, "?????? ????????? ????????? ??????!"));
		goalRepository.save(new Goal(5L, "?????? ????????? ??????!"));
	}

	/**
	 * [?????? ?????????] SUBJECT(??????)??? ???????????????.
	 */

	private void initSubject() {
		subjectRepository.save(new Subject(1L, "??????", staticImageUrl + "travle" + staticImageExt));
		subjectRepository.save(new Subject(2L, "??????&??????", staticImageUrl + "movie_music" + staticImageExt));
		subjectRepository.save(new Subject(3L, "???&??????", staticImageUrl + "company" + staticImageExt));
		subjectRepository.save(new Subject(4L, "??????", staticImageUrl + "shopping" + staticImageExt));
		subjectRepository.save(new Subject(5L, "??????", staticImageUrl + "food" + staticImageExt));
		subjectRepository.save(new Subject(6L, "??????&??????", staticImageUrl + "family" + staticImageExt));
		subjectRepository.save(new Subject(7L, "??????&??????", staticImageUrl + "workout" + staticImageExt));
		subjectRepository.save(new Subject(8L, "??????", staticImageUrl + "town" + staticImageExt));
		subjectRepository.save(new Subject(9L, "??????", staticImageUrl + "love" + staticImageExt));
	}
}
