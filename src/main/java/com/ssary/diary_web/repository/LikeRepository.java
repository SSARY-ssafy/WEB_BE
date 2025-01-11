package com.ssary.diary_web.repository;

import com.ssary.diary_web.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUserId(Long userId);
    Optional<Like> findByUserIdAndRecruitId(Long userId, Long recruitId);
    boolean existsByUserIdAndRecruitId(Long userId, Long recruitId);
}
