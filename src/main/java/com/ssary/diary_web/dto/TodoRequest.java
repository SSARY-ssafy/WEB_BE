package com.ssary.diary_web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TodoRequest {
    private Long userId; // 유저 ID
    private String title; // 할 일 제목
    private String content; // 할 일 상세 내용
    private LocalDate start; // 시작 날짜
    private LocalDate end; // 종료 날짜
}
