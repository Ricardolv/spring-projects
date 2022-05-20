package com.richard.batch.infrastructure.batch.jdbc.steps;

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
public class JdbcCursorStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step jdbcCursorStep(ItemReader<Client> jdbcCursorReader, ItemWriter<Client> jdbcCursorWriter) {
        return stepBuilderFactory
                .get("jdbcCursorStep")
                .<Client, Client>chunk(1)
                .reader(jdbcCursorReader)
                .writer(jdbcCursorWriter)
                .build();
    }

}
