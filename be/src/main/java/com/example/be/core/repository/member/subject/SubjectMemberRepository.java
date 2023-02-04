package com.example.be.core.repository.member.subject;

import com.example.be.core.domain.member.subject.SubjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectMemberRepository extends JpaRepository<SubjectMember, Long> {

	void deleteAllInBatchByMemberId(Long memberId);
}
