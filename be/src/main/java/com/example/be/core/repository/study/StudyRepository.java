package com.example.be.core.repository.study;

import com.example.be.core.domain.study.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StudyRepository extends JpaRepository<Study, Long> {

}
