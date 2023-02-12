package com.example.be.core.application.member;

import com.example.be.core.application.dto.response.SubjectResponse;
import com.example.be.core.domain.member.subject.Subject;
import com.example.be.core.repository.member.subject.SubjectRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SubjectService {

	private final SubjectRepository subjectRepository;

	public SubjectService(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}

	public List<SubjectResponse> findAll() {
		List<Subject> subjects = subjectRepository.findAll();
		return subjects.stream()
			.map(SubjectResponse::of)
			.collect(Collectors.toList());
	}

}
