package com.example.be.core.repository.member;

import com.example.be.core.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRespository extends JpaRepository<Member, Long> {

}
