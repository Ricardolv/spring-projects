package com.richard.batch.infrastructure.batch.processors.validation.reader;

import com.richard.batch.domain.ClientProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@RequiredArgsConstructor
@Configuration
public class ValidationProcessorReader {

    @StepScope
    @Bean
    public FlatFileItemReader<ClientProcessor> itemValidationProcessorReader(
            @Value("#{jobParameters['fileClientsValidation']}") Resource fileClientsValidation) {
        return new FlatFileItemReaderBuilder<ClientProcessor>()
                .name("validationProcessorReader")
                .resource(fileClientsValidation)
                .delimited()
                .names("name", "age", "email")
                .targetType(ClientProcessor.class)
                .build();
    }

}
