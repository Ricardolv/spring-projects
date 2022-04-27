package com.richard.ms1.domain;

import com.richard.ms1.infrastructure.persistence.entities.BaseDataOne;
import com.richard.ms1.infrastructure.persistence.repositories.BaseDataOneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BaseDataOneService {

    private final BaseDataOneRepository repository;

    public List<BaseDataOne> findAll() {

        log.info("{}.findAll() - INICIO.", this.getClass().getName());

        List<BaseDataOne> all = repository.findAll();

        log.info("{}.findAll() - FIM.", this.getClass().getName());

        return all;
    }

}
