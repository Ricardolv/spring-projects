package com.richard.infrastructure.resource.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CourseResoponse {

    @JsonProperty("_id")
    private Long id;
    private String name;
    private String category;
}
