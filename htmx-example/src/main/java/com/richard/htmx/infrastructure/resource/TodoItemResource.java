package com.richard.htmx.infrastructure.resource;

import com.richard.htmx.application.exceptions.TodoItemNotFoundException;
import com.richard.htmx.domain.TodoItemService;
import com.richard.htmx.infrastructure.persistence.TodoItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class TodoItemResource {

    private final TodoItemService service;

    @GetMapping
    public String index(Model model) {
        addAttributesForIndex(model, ListFilter.ALL);

        log.info("GET - index ALL");

        return "index";
    }

    @GetMapping("/active")
    public String indexActive(Model model) {
        addAttributesForIndex(model, ListFilter.ACTIVE);

        log.info("GET - index active ");

        return "index";
    }

    @GetMapping("/completed")
    public String indexCompleted(Model model) {
        addAttributesForIndex(model, ListFilter.COMPLETED);

        log.info("GET - indexCompleted completed ");

        return "index";
    }

    @PostMapping
    public String addNewTodoItem(@Valid @ModelAttribute("item") TodoItemFormData formData) {
        service.save(new TodoItem(formData.getTitle(), false));

        log.info("POST - addNewTodoItem data {} ", formData);

        return "redirect:/";
    }

    @PutMapping("/{id}/toggle")
    public String toggleSelection(@PathVariable("id") Long id) {
        TodoItem todoItem = service.findById(id);

        todoItem.setCompleted(!todoItem.isCompleted());
        service.save(todoItem);

        log.info("PUT - toggleSelection id {} ", id);

        return "redirect:/";
    }

    @PutMapping("/toggle-all")
    public String toggleAll() {
        List<TodoItem> todoItems = service.findAll();
        for (TodoItem todoItem : todoItems) {
            todoItem.setCompleted(!todoItem.isCompleted());
            service.save(todoItem);
        }

        log.info("PUT - toggleAll ");

        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id) {
        service.deleteById(id);

        log.info("DELETE - deleteTodoItem id {} ", id);

        return "redirect:/";
    }

    @DeleteMapping("/completed")
    public String deleteCompletedItems() {
        List<TodoItem> items = service.findAllByCompleted(true);
        for (TodoItem item : items) {
            service.deleteById(item.getId());
        }

        log.info("DELETE - deleteCompletedItems All ");

        return "redirect:/";
    }

    @PostMapping(headers = "HX-Request")
    public String htmxAddTodoItem(TodoItemFormData formData,
                                  Model model,
                                  HttpServletResponse response) {

        TodoItem item = service.save(new TodoItem(formData.getTitle(), false));
        model.addAttribute("item", toDto(item));

        response.setHeader("HX-Trigger", "itemAdded");

        log.info("POST - htmxAddTodoItem ");

        return "fragments :: todoItem";
    }

    @PutMapping(value = "/{id}/toggle", headers = "HX-Request")
    public String htmxToggleTodoItem(@PathVariable("id") Long id,
                                     Model model,
                                     HttpServletResponse response) {

        TodoItem todoItem = service.findById(id);

        todoItem.setCompleted(!todoItem.isCompleted());
        service.save(todoItem);

        model.addAttribute("item", toDto(todoItem));

        response.setHeader("HX-Trigger", "itemCompletionToggled");

        log.info("PUT - htmxToggleTodoItem ");

        return "fragments :: todoItem";
    }

    @DeleteMapping(value = "/{id}", headers = "HX-Request")
    @ResponseBody
    public String htmxDeleteTodoItem(@PathVariable("id") Long id,
                                     HttpServletResponse response) {
        service.deleteById(id);

        response.setHeader("HX-Trigger", "itemDeleted");

        log.info("DELETE - htmxDeleteTodoItem ");

        return "";
    }

    @GetMapping(value = "/active-items-count", headers = "HX-Request")
    public String htmxActiveItemsCount(Model model) {
        model.addAttribute("numberOfActiveItems", getNumberOfActiveItems());

        log.info("GET - htmxActiveItemsCount ");

        return "fragments :: active-items-count";
    }

    private void addAttributesForIndex(Model model,
                                       ListFilter listFilter) {
        model.addAttribute("item", new TodoItemFormData());
        model.addAttribute("filter", listFilter);
        model.addAttribute("todos", getTodoItems(listFilter));
        model.addAttribute("totalNumberOfItems", service.count());
        model.addAttribute("numberOfActiveItems", getNumberOfActiveItems());
        model.addAttribute("numberOfCompletedItems", getNumberOfCompletedItems());
    }

    private List<TodoItemDto> getTodoItems(ListFilter filter) {
        return switch (filter) {
            case ALL -> convertToDto(service.findAll());
            case ACTIVE -> convertToDto(service.findAllByCompleted(false));
            case COMPLETED -> convertToDto(service.findAllByCompleted(true));
        };
    }

    private List<TodoItemDto> convertToDto(List<TodoItem> todoItems) {
        return todoItems
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private TodoItemDto toDto(TodoItem todoItem) {
        return new TodoItemDto(todoItem.getId(),
                todoItem.getTitle(),
                todoItem.isCompleted());
    }

    private int getNumberOfActiveItems() {
        return service.countAllByCompleted(false);
    }

    private int getNumberOfCompletedItems() {
        return service.countAllByCompleted(true);
    }

    public static record TodoItemDto(long id, String title, boolean completed) {
    }

    public enum ListFilter {
        ALL,
        ACTIVE,
        COMPLETED
    }

}
