package com.richard.batch.infrastructure.batch.files.steps;

import com.richard.batch.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FixedWidthFileDelimitedStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step fixedWidthFileDelimitedStep(ItemReader<Client> fixedWidthFileDelimitedReader, ItemWriter<Client> fixedWidthFileDelimitedWriter) {
        return stepBuilderFactory
                .get("fixedWidthFileDelimitedStep")
                .<Client, Client>chunk(4)
                .reader(fixedWidthFileDelimitedReader)
                .writer(fixedWidthFileDelimitedWriter)
                .build();
    }
}
