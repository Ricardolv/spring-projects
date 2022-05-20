package com.richard.batch.infrastructure.batch.processors.classifier;

import com.richard.batch.domain.ClientProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ClassifierProcessorStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step classifierProcessorStep(FlatFileItemReader itemClassifierProcessorReader,
                                        ItemProcessor itemClassifierProcessor,
                                        ItemWriter itemClassifierProcessorWriter) {
        return stepBuilderFactory
                .get("classifierProcessorStep")
                .<ClientProcessor, ClientProcessor>chunk(1)
                .reader(itemClassifierProcessorReader)
                .processor(itemClassifierProcessor)
                .writer(itemClassifierProcessorWriter)
                .build();
    }

}
