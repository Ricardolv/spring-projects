package com.richard.batch.infrastructure.batch.skip;

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
public class SkipExceptionStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step skipStep(ItemReader<Client> skipExceptionReader, ItemWriter<Client> skipExceptionWriter) {
        return stepBuilderFactory
                .get("skipStep")
                .<Client, Client>chunk(11)
                .reader(skipExceptionReader)
                .writer(skipExceptionWriter)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(2)
                .build();
    }

}
