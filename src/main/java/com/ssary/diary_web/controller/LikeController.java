package com.ssary.diary_web.controller;

import com.ssary.diary_web.domain.Like;
import com.ssary.diary_web.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Like>> getLikes(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(likeService.getLikesByUserId(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addLikes(@RequestParam Integer userId, @RequestParam Integer recruitId) {
        try {
            likeService.addLike(userId, recruitId);
            return ResponseEntity.ok("Added to likes");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add like: " + e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeLike(@RequestParam Long userId, @RequestParam Long recruitId) {
        try {
            likeService.removeLike(userId, recruitId);
            return ResponseEntity.ok("Removed from likes");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to remove like: " + e.getMessage());
        }
    }
}
