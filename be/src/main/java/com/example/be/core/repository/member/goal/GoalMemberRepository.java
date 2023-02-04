package com.example.be.core.repository.member.goal;

import com.example.be.core.domain.member.goal.GoalMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalMemberRepository extends JpaRepository<GoalMember, Long> {

	void deleteAllInBatchByMemberId(Long memberId);

	List<GoalMember> findAllByMemberId(Long memberId);
}
