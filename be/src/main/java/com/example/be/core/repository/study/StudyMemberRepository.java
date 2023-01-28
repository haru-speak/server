package com.example.be.core.repository.study;

import com.example.be.core.domain.study.Study;
import com.example.be.core.domain.study.StudyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyMemberRepository extends JpaRepository<StudyMember, Long> {

}
