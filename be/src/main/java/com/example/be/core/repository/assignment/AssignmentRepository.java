package com.example.be.core.repository.assignment;

import com.example.be.core.domain.assignment.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

}
