package com.richard.batch.infrastructure.batch.processors.classifier.processor;

import com.richard.batch.domain.Client;
import org.springframework.batch.item.ItemProcessor;

public class ClientProcessor implements ItemProcessor<Client, Client> {

    @Override
    public Client process(Client client) throws Exception {
        System.out.println(String.format("\nAplicando regras de negócio no cliente %s ", client.getEmail()));
        return client;
    }

}
