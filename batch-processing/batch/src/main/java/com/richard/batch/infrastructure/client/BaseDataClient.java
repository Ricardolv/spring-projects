package com.richard.batch.infrastructure.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.richard.batch.application.config.RestTemplateConfig;
import com.richard.batch.domain.BaseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class BaseDataClient {

    private final RestTemplateConfig restTemplateConfig;
    private final ObjectMapper objectMapper;

    /**
     * request ms1
     * @return List
     */
    public List<BaseData> getAll() {
        try {

            var restTemplate = restTemplateConfig.restTemplateBase("http://localhost:8080");
            var responseEntity = restTemplate.getForEntity("/one-data", String.class);
            String body = responseEntity.getBody();
            return Arrays.asList(objectMapper.readValue(body, BaseData[].class));

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return List.of();
    }

    /**
     * request ms2
     */
    public void save(BaseData baseData) {
        try {

            var restTemplate = restTemplateConfig.restTemplateBase("http://localhost:8081");
            var responseEntity = restTemplate.postForEntity("/data", baseData, String.class);

            log.info("request status {}", responseEntity.getStatusCode().value());

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

    }

    /**
     * request ms4
     * @return List
     */
    public List<BaseData> getAllExternal() {
        try {

            var restTemplate = restTemplateConfig.restTemplateBase("http://localhost:8083");
            var responseEntity = restTemplate.getForEntity("/base-external", String.class);
            String body = responseEntity.getBody();
            return Arrays.asList(objectMapper.readValue(body, BaseData[].class));

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return List.of();
    }


}
