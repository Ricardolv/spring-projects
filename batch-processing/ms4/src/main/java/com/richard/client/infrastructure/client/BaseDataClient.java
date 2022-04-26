package com.richard.client.infrastructure.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class BaseDataClient {

    private final RestTemplate restTemplate;

    public Optional<String> getAll() {
        try {

            var responseEntity = this.restTemplate.getForEntity("/external", String.class);
            return Optional.ofNullable(responseEntity.getBody());

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return Optional.empty();
        }
    }

}
