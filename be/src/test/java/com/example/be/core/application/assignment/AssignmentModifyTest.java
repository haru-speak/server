package com.example.be.core.application.assignment;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.AssignmentService;
import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.dto.request.AssignmentRequest;
import com.example.be.core.application.dto.response.AssignmentDetailResponse;
import com.example.be.core.domain.assignment.Assignment;
import com.example.be.core.repository.assignment.AssignmentRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("서비스 테스트 : assignment 수정")
public class AssignmentModifyTest extends InitServiceTest {

  @Autowired
  private AssignmentService assignmentService;

  @Autowired
  private AssignmentRepository assignmentRepository;

  @Nested
  @DisplayName("과제를 수정할 때")
  class ModifyTest {

    @Test
    @DisplayName("과제의 제목, 내용이 변경된다.")
    void normal_modify() throws Exception {
      //given
      Long assignmentId = 1L;
      Long studyId = 1L;

      AssignmentRequest request = new AssignmentRequest(studyId,
          "수정한 제목",
          LocalDateTime.of(2022, 1, 30, 0, 0),
          "수정한 내용",
          "dummy-voice-record-data");

      //when
      AssignmentDetailResponse response = assignmentService.modify(assignmentId, request);

      //then
      Assignment after = assignmentRepository.findById(1L).get();
      assertThat(response.getTitle()).isEqualTo("수정한 제목");
      assertThat(response.getContent()).isEqualTo("수정한 내용");
    }
  }
}
