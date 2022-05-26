package com.richard.batch.infrastructure.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@RequiredArgsConstructor
@Configuration
public class DataMigrationJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job dataMigrationJob(@Qualifier("personMigrationStep") Step personMigrationStep,
                                @Qualifier("bankDataMigrationStep") Step bankDataMigrationStep) {
        return jobBuilderFactory
                .get("dataMigrationJob")
                .start(personMigrationStep)
                .next(bankDataMigrationStep)
                .build();
    }

}
