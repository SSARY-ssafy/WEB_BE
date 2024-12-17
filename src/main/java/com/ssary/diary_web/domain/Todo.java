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

    private String title; // 할 일 제목
    private boolean completed = false; // 기본 완료 상태는 false
    private LocalDate date; // 할 일 날짜
}
