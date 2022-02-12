package com.richard.htmx.infrastructure.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;



@Setter
@Getter
@Entity
@Table(name = "todo_item")
public class TodoItem {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    private boolean completed;

    public TodoItem() {
    }

    public TodoItem(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

}
