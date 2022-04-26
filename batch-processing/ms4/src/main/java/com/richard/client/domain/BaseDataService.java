package com.richard.client.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.richard.client.infrastructure.client.BaseDataClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BaseDataService {

    private final BaseDataClient client;
    private final ObjectMapper objectMapper;

    public List<BaseData> getAll() {
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

        return Arrays.asList(data);
    }

}
