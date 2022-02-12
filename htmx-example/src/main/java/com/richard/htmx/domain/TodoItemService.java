package com.richard.htmx.domain;

import com.richard.htmx.application.exceptions.TodoItemNotFoundException;
import com.richard.htmx.infrastructure.persistence.TodoItem;
import com.richard.htmx.infrastructure.persistence.TodoItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoItemService {

    private final TodoItemRepository repository;

    public TodoItem save(TodoItem todoItem) {
        return repository.save(todoItem);
    }

    public TodoItem findById(Long id) {
       return repository.findById(id)
               .orElseThrow(() -> new TodoItemNotFoundException(id));
    }

    public List<TodoItem> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<TodoItem> findAllByCompleted(boolean completed) {
        return repository.findAllByCompleted(completed);
    }

    public Long count() {
        return repository.count();
    }

    public int countAllByCompleted(boolean completed) {
        return repository.countAllByCompleted(completed);
    }

}
