package com.richard.batch.infrastructure.batch.step;

import com.richard.batch.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class IntegrationServiceStep {

    private final StepBuilderFactory stepBuilderFactory;

    @Value("${chunkSize}")
    private int chunkSize;

    @Bean
    public Step step(ItemReader<User> reader,
                     ItemWriter<User> writer) {
        return stepBuilderFactory
                .get("integrationServiceStep")
                .<User, User>chunk(chunkSize)
                .reader(reader)
                .writer(writer)
                .build();
    }
}
