package com.example.be.core.repository.study;

import com.example.be.core.domain.study.StudyMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyMemberRepository extends JpaRepository<StudyMember, Long> {
    List<StudyMember> findStudyMembersByStudy_Id(Long studyId);

    List<StudyMember> findStudyMembersByMember_Id(Long memberId);

    StudyMember findStudyMemberByStudy_IdAndLeaderIsTrue(Long studyId);

    Integer countStudyMemberByStudy_Id(Long studyId);
}
