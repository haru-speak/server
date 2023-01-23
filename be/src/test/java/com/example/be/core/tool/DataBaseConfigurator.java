package com.example.be.core.tool;

import com.example.be.common.exception.speakinglog.NotFoundMemberIdException;
import com.example.be.core.domain.member.Member;
import com.example.be.core.domain.speakinglog.SpeakingLog;
import com.example.be.core.repository.member.MemberRepository;
import com.example.be.core.repository.speakinglog.SpeakingLogRepository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class DataBaseConfigurator implements InitializingBean {

	private static final String SET_FOREIGN_KEY_CHECKS = "SET FOREIGN_KEY_CHECKS = ";
	private static final String TRUNCATE_TABLE = "TRUNCATE TABLE ";
	private static final String TABLE = "TABLE";
	private static final String TABLE_NAME = "table_name";

	private static final int NUMBER_OF_MEMBER = 5;
	private static final int NUMBER_OF_SPEAKING_LOG = 3;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private SpeakingLogRepository speakingLogRepository;

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
	}

	/**
	 * Member
	 * 도메인 객체를 NUMBER_OF_MEMBER 만큼 생성합니다.
	 */
	private void initMember() {
		for (int i = 1; i <= NUMBER_OF_MEMBER; i++) {
			memberRepository.save(
				new Member(
					"member" + i,
					"member-email" + i + "@google.com",
					"password1234" + i,
					"profileImage" + i
				));
		}
	}

	/**
	 * SpeakingLog
	 * 도메인 객체를 각 멤버 마다 NUMBER_OF_SPEAKING_LOG 만큼 생성합니다.
	 */
	private void initSpeakingLog() {
		for (int i = 1; i <= NUMBER_OF_MEMBER; i++) {
			Member member = memberRepository.findById((long) i)
				.orElseThrow(NotFoundMemberIdException::new);

			for (int j = 1; j <= NUMBER_OF_SPEAKING_LOG; j++) {
				speakingLogRepository.save(
					new SpeakingLog(
						member,
						"speaking-log-title"+i+j,
						"dummy-voice-record"+i+j,
						"dummy-voice-text"+i+j
					));
			}
		}

	}
}
