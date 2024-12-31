package com.ssary.diary_web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class TodoRequest {
    private String title; // 할 일 제목
    private LocalDate date; // 할 일 날짜
    private String content; // 할 일 내용 (선택)
    private LocalDate start; // 시작 날짜 (선택)
    private LocalDate end; // 종료 날짜 (선택)
}
