package com.ssary.diary_web.controller;

import com.ssary.diary_web.dto.TodoRequest;
import com.ssary.diary_web.dto.TodoResponse;
import com.ssary.diary_web.service.TodoService;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 할 일 추가
    @PostMapping
    public TodoResponse addTodo(@RequestBody TodoRequest request) {
        return todoService.addTodo(request);
    }

    // 날짜별 할 일 조회 (유저 기반)
    @GetMapping
    public List<TodoResponse> getTodosByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam Long userId) {
        return todoService.getTodosByDate(start, end, userId);
    }

    // 완료 상태 변경
    @PatchMapping("/{id}/complete")
    public TodoResponse updateCompletion(@PathVariable Long id, @RequestParam boolean completed) {
        return todoService.updateCompletion(id, completed);
    }

    // 할 일 삭제
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
