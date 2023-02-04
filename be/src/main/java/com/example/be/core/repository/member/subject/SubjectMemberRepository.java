package com.example.be.core.repository.member.subject;

import com.example.be.core.domain.member.subject.SubjectMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectMemberRepository extends JpaRepository<SubjectMember, Long> {

	void deleteAllInBatchByMemberId(Long memberId);

	List<SubjectMember> findAllByMemberId(Long memberId);
}
