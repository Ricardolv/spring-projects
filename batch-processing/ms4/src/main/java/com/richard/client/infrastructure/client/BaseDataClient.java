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
        log.info("{}.getAll() - INICIO.", this.getClass().getName());

        try {
            var responseEntity = this.restTemplate.getForEntity("/external", String.class);

            log.info("{}.getAll() - FIM.", this.getClass().getName());

            return Optional.ofNullable(responseEntity.getBody());
        } catch (Exception ex) {

            log.error(ex.getMessage());
            log.info("{}.getAll() - FIM.", this.getClass().getName());

            return Optional.empty();
        }
    }

}
