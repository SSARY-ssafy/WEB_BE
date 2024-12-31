package com.ssary.diary_web.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "todo") // 테이블 이름 매핑
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id") // todo_id와 매핑
    private Long id;

    private String title;

    @Column(nullable = false, columnDefinition = "VARCHAR(45) DEFAULT ''")
    private String content;

    private LocalDate date;

    @Column(nullable = false)
    private LocalDate start;

    @Column(nullable = false)
    private LocalDate end;

    @Column(name = "complete") // complete 필드 매핑
    private boolean completed;

    @Column(name = "user_id", nullable = false)
    private Integer userId;
}

