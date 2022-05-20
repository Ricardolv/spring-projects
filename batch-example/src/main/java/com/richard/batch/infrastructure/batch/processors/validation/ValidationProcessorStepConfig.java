package com.richard.batch.infrastructure.batch.processors.validation;

import com.richard.batch.domain.ClientProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ValidationProcessorStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step validationProcessorStep(ItemReader<ClientProcessor> itemValidationProcessorReader,
                                        ItemProcessor<ClientProcessor, ClientProcessor> itemValidationProcessor,
                                        ItemWriter<ClientProcessor> itemValidationProcessorWriter) {
        return stepBuilderFactory
                .get("validationProcessorStep")
                .<ClientProcessor, ClientProcessor>chunk(1)
                .reader(itemValidationProcessorReader)
                .processor(itemValidationProcessor)
                .writer(itemValidationProcessorWriter)
                .build();
    }

}
