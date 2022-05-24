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
public class WrittenFlatStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step writtenFlatStep(ItemReader<Client> writtenFlatReader,
                                    ItemWriter<Client> writtenFlatWriter) {
        return stepBuilderFactory
                .get("writtenFlatStep")
                .<Client, Client>chunk(1)
                .reader(writtenFlatReader)
                .writer(writtenFlatWriter)
                .build();
    }
}
