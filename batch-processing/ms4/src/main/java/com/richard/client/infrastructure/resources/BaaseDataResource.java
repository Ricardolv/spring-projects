package com.richard.client.infrastructure.resources;


import com.richard.client.domain.BaseData;
import com.richard.client.domain.BaseDataService;
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
@RequestMapping(path = "/base-external")
public class BaaseDataResource {

    private final BaseDataService service;

    @GetMapping
    public ResponseEntity<List<BaseData>> getAll() {
        log.info("{}.getAll() - INICIO.", this.getClass().getName());

        var response = service.getAll();

        log.info("{}.getAll() - FIM.", this.getClass().getName());
        return ResponseEntity.ok(response);
    }
}
