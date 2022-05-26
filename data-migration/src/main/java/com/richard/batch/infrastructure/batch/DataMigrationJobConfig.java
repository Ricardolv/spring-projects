package com.richard.batch.infrastructure.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class DataMigrationJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job dataMigrationJob(Step personMigrationStep,
                                Step bankDataMigrationStep) {
        return jobBuilderFactory
                .get("dataMigrationJob")
                .start(personMigrationStep)
                .next(bankDataMigrationStep)
                .build();
    }

}
