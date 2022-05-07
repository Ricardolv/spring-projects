package com.richard.batch.infrastructure.batch.files.steps;

import com.richard.batch.domain.Client;
import com.richard.batch.infrastructure.batch.files.readers.ClientTransactionReader;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FixedWidthFileMultipleStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step fixedWidthFileMultipleStep(FlatFileItemReader fixedWidthFileMultipleReader,
                                           ItemWriter fixedWidthFileMultipleWriter) {
        return stepBuilderFactory
                .get("fixedWidthFileMultipleStep")
                .<Client, Client>chunk(1)
                .reader(new ClientTransactionReader(fixedWidthFileMultipleReader))
                .writer(fixedWidthFileMultipleWriter)
                .build();
    }
}
