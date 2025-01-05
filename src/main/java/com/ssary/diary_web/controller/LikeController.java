//새로 추가 (김서현)
package com.ssary.diary_web.controller;

import com.ssary.diary_web.domain.Like;
import com.ssary.diary_web.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Like>> getFavorites(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(likeService.getFavoritesByUserId(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFavorite(@RequestParam Long userId, @RequestParam String companyId) {
        try {
            likeService.addFavorite(userId, companyId);
            return ResponseEntity.ok("Added to likes");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add like: " + e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFavorite(@RequestParam Long userId, @RequestParam String companyId) {
        try {
            likeService.removeFavorite(userId, companyId);
            return ResponseEntity.ok("Removed from likes");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to remove like: " + e.getMessage());
        }
    }

}
