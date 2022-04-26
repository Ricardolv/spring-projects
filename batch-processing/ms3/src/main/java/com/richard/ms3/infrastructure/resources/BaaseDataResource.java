package com.richard.ms3.infrastructure.resources;

import com.richard.ms3.infrastructure.resources.request.BaseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/external")
public class BaaseDataResource {

    @GetMapping
    public ResponseEntity<List<BaseData>> getAll() {

        var baseOne = BaseData.builder()
                .nome("NOME 1")
                .descricao("Descrição 1")
                .email("nome1.teste@teste.com")
                .senha("123")
                .build();
        var baseTwo = BaseData.builder()
                .nome("NOME 2")
                .descricao("Descrição 2")
                .email("nome2.teste@teste.com")
                .senha("123")
                .build();
        var baseThree = BaseData.builder()
                .nome("NOME 3")
                .descricao("Descrição 3")
                .email("nome3.teste@teste.com")
                .senha("123")
                .build();

        var list = Arrays.asList(baseOne, baseTwo, baseThree);

        return ResponseEntity.ok(list);
    }
}
