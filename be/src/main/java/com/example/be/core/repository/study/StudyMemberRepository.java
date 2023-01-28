package com.example.be.core.repository.study;

import com.example.be.core.domain.study.StudyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyMemberRepository extends JpaRepository<StudyMember, Long> {

    //test 용으로 작성 커밋 후 삭제
    StudyMember findStudyMemberByMember_Id(Long memberId);
}
