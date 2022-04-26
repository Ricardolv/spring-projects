package com.richard.ms2.infrastructure.resources;

import com.richard.ms2.domain.BaseDataService;
import com.richard.ms2.infrastructure.resources.request.BaseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "data")
public class BaseDataResource {

    private final BaseDataService service;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody BaseData baseData) {

        service.save(baseData);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
