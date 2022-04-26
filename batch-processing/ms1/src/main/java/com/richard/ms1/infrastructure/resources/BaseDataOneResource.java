package com.richard.ms1.infrastructure.resources;

import com.richard.ms1.domain.BaseDataOneService;
import com.richard.ms1.infrastructure.persistence.entities.BaseDataOne;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "one-data")
public class BaseDataOneResource {

    private final BaseDataOneService service;

    @GetMapping
    public ResponseEntity<List<BaseDataOne>> getAll() {
        var response = service.findAll();
        return ResponseEntity.ok(response);
    }

}
