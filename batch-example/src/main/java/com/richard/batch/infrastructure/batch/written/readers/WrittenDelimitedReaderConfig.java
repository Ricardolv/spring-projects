package com.richard.batch.infrastructure.batch.written.readers;

import com.richard.batch.domain.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class WrittenDelimitedReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Client> writtenDelimitedReader(
            @Value("#{jobParameters['fileClientsDelimit']}") Resource fileClientsDelimit) {
        return new FlatFileItemReaderBuilder<Client>()
                .name("writtenDelimitedReader")
                .resource(fileClientsDelimit)
                .delimited()
                .names(new String[] {"name", "lastName", "age", "email"})
                .targetType(Client.class)
                .build();
    }

}
