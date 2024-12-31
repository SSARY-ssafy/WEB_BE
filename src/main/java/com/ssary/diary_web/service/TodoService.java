package com.ssary.diary_web.service;

import com.ssary.diary_web.domain.Todo;
import com.ssary.diary_web.dto.TodoRequest;
import com.ssary.diary_web.dto.TodoResponse;
import com.ssary.diary_web.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 할 일 추가
    public TodoResponse addTodo(TodoRequest request, Integer userId) {
        LocalDate startDate = request.getStart() != null ? request.getStart() : LocalDate.now();
        LocalDate endDate = request.getEnd() != null ? request.getEnd() : LocalDate.now().plusDays(1);

        Todo todo = Todo.builder()
                .title(request.getTitle())
                .content(request.getContent() != null ? request.getContent() : "") // content 기본값
                .date(request.getDate())
                .start(startDate) // start 값 설정
                .end(endDate) // end 값 설정
                .completed(false)
                .userId(userId)
                .build();
        todoRepository.save(todo);
        return convertToResponse(todo);
    }



    // 날짜별 할 일 조회
    public List<TodoResponse> getTodosByDate(LocalDate date) {
        return todoRepository.findByDate(date)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // 할 일 삭제
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("할 일을 찾을 수 없습니다."));
        todoRepository.delete(todo);
    }

    // 상태 변경 (뷰에서 요청 시 활용)
    public TodoResponse updateCompletion(Long id, boolean completed) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("할 일을 찾을 수 없습니다."));
        todo.setCompleted(completed);
        todoRepository.save(todo);
        return convertToResponse(todo);
    }

    private TodoResponse convertToResponse(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .completed(todo.isCompleted())
                .date(todo.getDate())
                .content(todo.getContent())
                .start(todo.getStart())
                .end(todo.getEnd())
                .build();
    }
}
