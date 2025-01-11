package com.ssary.diary_web.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "\"like\"")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Integer likeId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "recruit_id", nullable = false)
    private Integer recruitId;

    public Integer getLikeId() {
        return likeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getRecruitId() {
        return recruitId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setRecruitId(Integer recruitId) {
        this.recruitId = recruitId;
    }
}
