package com.richard.ms1.infrastructure.resources;

import com.richard.ms1.domain.BaseDataOneService;
import com.richard.ms1.infrastructure.persistence.entities.BaseDataOne;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "one-data")
public class BaseDataOneResource {

    private final BaseDataOneService service;

    @GetMapping
    public ResponseEntity<List<BaseDataOne>> getAll() {

        log.info("{}.getAll() - INICIO.", this.getClass().getName());

        var response = service.findAll();

        log.info("{}.getAll() - FIM.", this.getClass().getName());

        return ResponseEntity.ok(response);
    }

}
