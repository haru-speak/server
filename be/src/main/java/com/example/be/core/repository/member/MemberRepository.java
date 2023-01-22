package com.example.be.core.repository.member;

import com.example.be.core.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  /**
   * 회원 가입시 중복된 회원이 있는지 검사하기 위해 이메일로 회원을 검사하는 메소드
   * */
  Member findByEmail(String email);

}
