package com.richard.batch.infrastructure.batch.processors.composite;

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
public class CompositeProcessorStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step compositeProcessorStep(ItemReader<ClientProcessor> itemCompositeProcessorReader,
                                       ItemProcessor<ClientProcessor, ClientProcessor> itemCompositeProcessor,
                                       ItemWriter<ClientProcessor> itemCompositeProcessorWriter) {
        return stepBuilderFactory
                .get("compositeProcessorStep")
                .<ClientProcessor, ClientProcessor>chunk(1)
                .reader(itemCompositeProcessorReader)
                .processor(itemCompositeProcessor)
                .writer(itemCompositeProcessorWriter)
                .build();
    }

}
