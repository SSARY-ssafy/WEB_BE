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

    public void addFavorite(Long userId, Long companyId) {
        if (likeRepository.existsByUserIdAndCompanyId(userId, companyId)) {
            throw new IllegalStateException("Favorite already exists");
        }
        Like favorite = new Like();
        favorite.setUserId(userId);
        favorite.setCompanyId(companyId);
        likeRepository.save(favorite);
    }

    public void removeFavorite(Long userId, Long companyId) {
        Like favorite = likeRepository.findByUserIdAndCompanyId(userId, companyId)
                .orElseThrow(() -> new IllegalStateException("Favorite not found"));
        likeRepository.delete(favorite);
    }
}
