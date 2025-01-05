package com.ssary.diary_web.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id") // 테이블의 기본 키와 매핑
    private Long id;

    @Column(name = "user_id", nullable = false) // 테이블의 user_id와 매핑
    private Long userId;

    @Column(name = "company_id", nullable = false) // company_id를 String으로 변경
    private String companyId;

    @Column(name = "created_at") // created_at와 매핑
    private Timestamp createdAt;
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
