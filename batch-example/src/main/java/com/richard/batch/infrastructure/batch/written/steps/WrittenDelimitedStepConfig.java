package com.richard.batch.infrastructure.batch.written.steps;

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
public class WrittenDelimitedStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step writtenDelimitedStep(ItemReader<Client> writtenDelimitedReader,
                                     ItemWriter<Client> writtenDelimitedWriter) {
        return stepBuilderFactory
                .get("writtenDelimitedStep")
                .<Client, Client>chunk(1)
                .reader(writtenDelimitedReader)
                .writer(writtenDelimitedWriter)
                .build();
    }
}
