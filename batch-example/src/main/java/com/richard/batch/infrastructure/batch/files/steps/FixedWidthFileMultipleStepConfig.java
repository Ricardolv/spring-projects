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
public class FixedWidthFileMultipleStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step fixedWidthFileMultipleStep(ItemReader fixedWidthFileMultipleReader, ItemWriter fixedWidthFileMultipleWriter) {
        return stepBuilderFactory
                .get("fixedWidthFileMultipleStep")
                .<Client, Client>chunk(1)
                .reader(fixedWidthFileMultipleReader)
                .writer(fixedWidthFileMultipleWriter)
                .build();
    }
}
