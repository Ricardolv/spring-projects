package com.richard.client.infrastructure.resources;


import com.richard.client.domain.BaseData;
import com.richard.client.domain.BaseDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/base-external")
public class BaaseDataResource {

    private final BaseDataService service;

    @GetMapping
    public ResponseEntity<List<BaseData>> getAll() {
        var response = service.getAll();
        return ResponseEntity.ok(response);
    }
}
