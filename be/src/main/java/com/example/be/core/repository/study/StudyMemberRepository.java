package com.example.be.core.repository.study;

import com.example.be.core.domain.study.StudyMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StudyMemberRepository extends JpaRepository<StudyMember, Long> {
    List<StudyMember> findStudyMembersByStudyId(Long studyId);

    List<StudyMember> findStudyMembersByMemberId(Long memberId);

    StudyMember findStudyMemberByStudyIdAndLeaderIsTrue(Long studyId);

    Integer countStudyMemberByStudyId(Long studyId);
}
