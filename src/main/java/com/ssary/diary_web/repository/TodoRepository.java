package com.ssary.diary_web.repository;

import com.ssary.diary_web.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // 특정 날짜에 해당하는 투두를 조회하기 위해 메서드 추가
    List<Todo> findByDate(LocalDate date);

    // 날짜 범위와 사용자 ID를 기준으로 투두를 조회
    List<Todo> findByStartBetweenAndUserId(LocalDate start, LocalDate end, Integer userId);
}
