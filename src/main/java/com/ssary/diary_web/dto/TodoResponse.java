package com.ssary.diary_web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class TodoResponse {
    private Long id; // 할 일 ID
    private String title; // 할 일 제목
    private boolean completed; // 완료 상태
    private LocalDate date; // 할 일 날짜
    private String content; // 할 일 내용
    private LocalDate start; // 시작 날짜
    private LocalDate end; // 종료 날짜
}
