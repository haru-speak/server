package com.example.be.core.repository.member;

import com.example.be.core.domain.member.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  	Optional<Member> findByEmail(String email);
	Optional<Member> findByUniqueId(String uniqueId);
	boolean existsByUniqueId(String uniqueId);

}
