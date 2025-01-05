// 새로 추가해야함 
package com.ssary.diary_web.service;

import com.ssary.diary_web.domain.Like;
import com.ssary.diary_web.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public List<Like> getFavoritesByUserId(Long userId) {
        return likeRepository.findByUserId(userId);
    }

    public void addFavorite(Long userId, String companyId) {
        if (likeRepository.existsByUserIdAndCompanyId(userId, companyId)) {
            throw new IllegalStateException("Like already exists");
        }
        Like like = new Like();
        like.setUserId(userId);
        like.setCompanyId(companyId);
        likeRepository.save(like);
    }

    public void removeFavorite(Long userId, String companyId) {
        Like like = likeRepository.findByUserIdAndCompanyId(userId, companyId)
                .orElseThrow(() -> new IllegalStateException("Like not found"));
        likeRepository.delete(like);
    }
}
