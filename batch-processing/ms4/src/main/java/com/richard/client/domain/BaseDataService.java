package com.richard.client.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.richard.client.infrastructure.client.BaseDataClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BaseDataService {

    private final BaseDataClient client;
    private final ObjectMapper objectMapper;

    public List<BaseData> getAll() {

        List<BaseData> response = List.of();

        client.getAll().ifPresent(json -> {

            try {
                BaseData[] data = objectMapper.readValue(json, BaseData[].class);

                Arrays.asList(data).forEach(item -> response.add(item));

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        });


        return response;
    }

}
