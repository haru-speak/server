package com.example.be.core.application.goal_subject;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.dto.response.SubjectResponse;
import com.example.be.core.application.member.SubjectService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("서비스 테스트 : Subject 전체 조회")
class SubjectFindAllTest extends InitServiceTest {

	@Autowired
	private SubjectService subjectService;

	@Nested
	@DisplayName("주제를 전체 조회할 때")
	class FindAllTest {

		@Nested
		@DisplayName("정상적인 요청이라면")
		class NormalTest {

			@Test
			void find_all_subjects_test() {
				//give & when
				List<SubjectResponse> subjects = subjectService.findAll();

				//then
				assertThat(subjects).hasSize(9);
				assertThat(subjects.get(0).getSubjectId()).isEqualTo(1L);
				assertThat(subjects.get(1).getSubjectId()).isEqualTo(2L);
				assertThat(subjects.get(2).getSubjectId()).isEqualTo(3L);
				assertThat(subjects.get(3).getSubjectId()).isEqualTo(4L);
				assertThat(subjects.get(4).getSubjectId()).isEqualTo(5L);
				assertThat(subjects.get(5).getSubjectId()).isEqualTo(6L);
				assertThat(subjects.get(6).getSubjectId()).isEqualTo(7L);
				assertThat(subjects.get(7).getSubjectId()).isEqualTo(8L);
				assertThat(subjects.get(8).getSubjectId()).isEqualTo(9L);
			}
		}
	}
}
