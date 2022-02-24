package com.richard.domain;

import com.richard.infrastructure.persistence.CourseEntity;
import com.richard.infrastructure.persistence.CourseRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public List<CourseEntity> list() {
        return courseRepository.findAll();
    }
}
