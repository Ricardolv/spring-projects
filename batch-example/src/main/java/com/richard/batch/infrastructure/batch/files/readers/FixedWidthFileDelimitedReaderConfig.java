package com.richard.batch.infrastructure.batch.files.readers;

import com.richard.batch.domain.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class FixedWidthFileDelimitedReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Client> fixedWidthFileDelimitedReader(
            @Value("#{jobParameters['fileClientsDelimit']}") Resource fileClients) {
        return new FlatFileItemReaderBuilder<Client>()
                .name("fixedWidthFileDelimitedReader")
                .resource(fileClients)
                .delimited()
                .names(new String[] {"name", "lastName", "age", "email"})
                .targetType(Client.class)
                .build();
    }

}
