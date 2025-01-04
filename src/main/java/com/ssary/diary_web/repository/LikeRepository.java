package com.ssary.diary_web.repository;

import com.ssary.diary_web.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // Import 추가
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUserId(Long userId);
    Optional<Like> findByUserIdAndCompanyId(Long userId, String companyId);
    boolean existsByUserIdAndCompanyId(Long userId, String companyId);
}
