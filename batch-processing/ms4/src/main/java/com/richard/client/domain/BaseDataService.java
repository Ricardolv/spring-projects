package com.richard.client.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.richard.client.infrastructure.client.BaseDataClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BaseDataService {

    private final BaseDataClient client;
    private final ObjectMapper objectMapper;

    public List<BaseData> getAll() {
        log.info("{}.getAll() - INICIO.", this.getClass().getName());

        BaseData[] data = new BaseData[0];
        Optional<String> jsonOptional = client.getAll();

        if (jsonOptional.isEmpty()) {
            return List.of();
        }

        String json = jsonOptional.get();

        try {
            data = objectMapper.readValue(json, BaseData[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        log.info("{}.getAll() - FIM.", this.getClass().getName());
        return Arrays.asList(data);
    }

}
