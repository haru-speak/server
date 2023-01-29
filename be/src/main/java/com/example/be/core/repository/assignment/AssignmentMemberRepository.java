package com.example.be.core.repository.assignment;

import com.example.be.core.domain.assignment.AssignmentMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentMemberRepository extends JpaRepository<AssignmentMember, Long> {

  List<AssignmentMember> findAssignmentMemberByMemberId(Long memberId);
}
