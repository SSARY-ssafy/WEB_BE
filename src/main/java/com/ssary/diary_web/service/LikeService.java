package com.ssary.diary_web.service;

import com.ssary.diary_web.domain.Like;
import com.ssary.diary_web.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public List<Like> getLikesByUserId(Long userId) {
        return likeRepository.findByUserId(userId);
    }

    public void addLike(Integer userId, Integer recruitId) {
        if (likeRepository.existsByUserIdAndRecruitId( Long.valueOf(userId), Long.valueOf(recruitId))) {
            throw new IllegalStateException("Like already exists");
        }
        Like like = new Like();
        like.setUserId(userId);
        like.setRecruitId(recruitId);
        likeRepository.save(like);
    }

    public void removeLike(Long userId, Long recruitId) {
        Like like = likeRepository.findByUserIdAndRecruitId(userId, recruitId)
                .orElseThrow(() -> new IllegalStateException("Like not found"));
        likeRepository.delete(like);
    }
}
