package com.richard.infrastructure.resource;


import com.richard.domain.CourseService;
import com.richard.infrastructure.resource.response.CourseResoponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.richard.infrastructure.resource.mapper.CourseMapper.INSTANCE;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseResource {

    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseResoponse>> list() {
        return ResponseEntity.ok(INSTANCE.map(courseService.list()));
    }

}
