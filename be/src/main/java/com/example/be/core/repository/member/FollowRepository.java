package com.example.be.core.repository.member;

import com.example.be.core.domain.member.Follow;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

	Integer countByFollowerId(Long memberId);

	Integer countByFollowingId(Long memberId);

	Optional<Follow> findByFollowerIdAndFollowingId(Long followerId, Long followingId);

}
