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
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // 유저 ID
    private String title; // 할 일 제목
    private String content; // 할 일 상세 내용
    private boolean completed = false; // 기본 완료 상태는 false
    private LocalDate start; // 시작 날짜
    private LocalDate end; // 종료 날짜
}
