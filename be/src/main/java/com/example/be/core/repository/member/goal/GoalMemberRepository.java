package com.example.be.core.repository.member.goal;

import com.example.be.core.domain.member.goal.GoalMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalMemberRepository extends JpaRepository<GoalMember, Long> {

	void deleteAllInBatchByMemberId(Long memberId);
}
