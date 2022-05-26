package com.richard.batch.infrastructure.client;

import com.richard.batch.domain.Client;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SystemClient {

    private static final String URI = "http://my-json-server.typicode.com/giuliana-bezerra/demo/profile/%d";

    public Client getClient(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Client> response = restTemplate.getForEntity(URI, Client.class);

        if (response.getStatusCode() != HttpStatus.OK)
            throw new ValidationException("Cliente não encontrado!");

        return response.getBody();
    }


}
