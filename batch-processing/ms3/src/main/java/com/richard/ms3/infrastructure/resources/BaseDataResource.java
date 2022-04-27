package com.richard.ms3.infrastructure.resources;

import com.richard.ms3.infrastructure.resources.request.BaseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/external")
public class BaseDataResource {

    @GetMapping
    public ResponseEntity<List<BaseData>> getAll() {

        log.info("{}.getAll() - INICIO.", this.getClass().getName());

        var baseOne = BaseData.builder()
                .nome("NOME 1 external")
                .descricao("Descrição 1 external")
                .email("nome1.teste@teste.external.com")
                .senha("123")
                .build();
        var baseTwo = BaseData.builder()
                .nome("NOME 2 external")
                .descricao("Descrição 2 external")
                .email("nome2.teste@teste.external.com")
                .senha("123")
                .build();
        var baseThree = BaseData.builder()
                .nome("NOME 3 external")
                .descricao("Descrição 3 external")
                .email("nome3.teste@teste.external.com")
                .senha("123")
                .build();

        var list = Arrays.asList(baseOne, baseTwo, baseThree);

        log.info("{}.getAll() - FIM.", this.getClass().getName());
        return ResponseEntity.ok(list);
    }
}
