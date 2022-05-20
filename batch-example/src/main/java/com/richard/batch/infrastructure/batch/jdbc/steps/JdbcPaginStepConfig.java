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
public class JdbcPaginStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step jdbcPaginStep(ItemReader<Client> jdbcPaginReader, ItemWriter<Client> jdbcPaginWriter) {
        return stepBuilderFactory
                .get("jdbcPaginStep")
                .<Client, Client>chunk(1)
                .reader(jdbcPaginReader)
                .writer(jdbcPaginWriter)
                .build();
    }

}
