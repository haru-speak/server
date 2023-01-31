package com.example.be.core.application;

import com.example.be.core.repository.assignment.AssignmentMemberRepository;
import com.example.be.core.repository.assignment.AssignmentRepository;
import com.example.be.core.repository.speakinglog.SpeakingLogRepository;
import com.example.be.core.repository.study.StudyMemberRepository;
import com.example.be.core.repository.study.StudyRepository;
import com.example.be.tool.DataBaseConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public abstract class InitServiceTest {

	@Autowired
	protected SpeakingLogService speakingLogService;

	@Autowired
	protected SpeakingLogRepository speakingLogRepository;

	@Autowired
	protected StudyService studyService;

	@Autowired
	protected StudyRepository studyRepository;

	@Autowired
	protected StudyMemberRepository studyMemberRepository;

	@Autowired
	protected AssignmentService assignmentService;

	@Autowired
	protected AssignmentMemberRepository assignmentMemberRepository;

	@Autowired
	protected AssignmentRepository assignmentRepository;

	@Autowired
	private DataBaseConfigurator dbConfigurator;

	@BeforeEach
	void setUpDataBase() {
		dbConfigurator.clear();
		dbConfigurator.initDataSource();
	}
}
