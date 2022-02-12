package com.richard.htmx.infrastructure.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Setter
@Getter
public class TodoItemFormData {

    @NotBlank
    private String title;

}
