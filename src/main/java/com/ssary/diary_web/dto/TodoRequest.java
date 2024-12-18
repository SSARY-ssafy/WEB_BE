package com.ssary.diary_web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TodoRequest {
    private String title; // 할 일 제목
    private LocalDate date; // 할 일 날짜
}
