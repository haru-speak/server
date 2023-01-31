package com.example.be.core.application.assignment;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.dto.request.AssignmentRequest;
import com.example.be.core.application.dto.response.AssignmentDetailResponse;
import com.example.be.core.domain.assignment.AssignmentMember;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("서비스 테스트 : Assignment 생성")
class AssignmentCreateTest extends InitServiceTest {

  @Nested
  @DisplayName("새로운 과제를 생성할 때")
  class CreateTest {

    @Nested
    @DisplayName("정상적인 요청이라면")
    class NormalTest {

      @Test
      @DisplayName("로그인 한 멤버가 속해 있는 스터디의 과제가 생성된다.")
      void normal_create() {

        //given
        Long memberId = 1L;
        Long studyId = 1L;
        AssignmentRequest request = new AssignmentRequest(studyId,
            "연의 과제",
            LocalDateTime.of(2022, 1, 19, 0, 0),
            "연의 과제입니다",
            "dummy-voice-record-data");
        //when
        AssignmentDetailResponse response = assignmentService.create(request, memberId);

        //then
        List<AssignmentMember> assignmentMembers = assignmentMemberRepository.findAssignmentMembersByMemberId(2L);
        assertThat(assignmentMembers.size()).isEqualTo(4);

        assertThat(response.getAssignmentId()).isEqualTo(4);
        assertThat(response.getTitle()).isEqualTo("연의 과제");
        assertThat(response.getContent()).isEqualTo("연의 과제입니다");
        assertThat(response.getVoiceRecord()).isEqualTo("dummy-voice-record-data");
      }
    }
  }
}
