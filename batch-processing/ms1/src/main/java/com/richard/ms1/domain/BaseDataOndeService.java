package com.richard.ms1.domain;

import com.richard.ms1.infrastructure.persistence.entities.BaseDataOne;
import com.richard.ms1.infrastructure.persistence.repositories.BaseDataOneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BaseDataOndeService {

    private final BaseDataOneRepository repository;

    public List<BaseDataOne> findAll() {
        return repository.findAll();
    }

}
