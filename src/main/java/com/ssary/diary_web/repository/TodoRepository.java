package com.ssary.diary_web.repository;

import com.ssary.diary_web.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByStartBetweenAndUserId(LocalDate start, LocalDate end, Long userId);
}