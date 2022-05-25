package com.richard.batch.infrastructure.batch.written.writers;

import com.richard.batch.domain.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class WrittenDelimitedWriterConfig {

    @StepScope
    @Bean
    public FlatFileItemWriter<Client> writtenDelimitedWriter(
            @Value("#{jobParameters['fileClientsDelimitedExit']}") Resource fileClientsDelimitedExit) {
        return new FlatFileItemWriterBuilder<Client>()
                .name("writtenDelimitedWriter")
                .resource(fileClientsDelimitedExit)
                .delimited()
                .names("name", "lastName", "age", "email")
                .build();
    }

}
