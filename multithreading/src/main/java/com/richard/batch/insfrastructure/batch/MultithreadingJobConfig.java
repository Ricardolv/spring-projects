package com.richard.batch.insfrastructure.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class MultithreadingJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job multithreadingStepJob(
            @Qualifier("migrationPersonStep") Step migrationPersonStep,
            @Qualifier("migrationBankDataStep") Step migrationBankDataStep) {
        return jobBuilderFactory
                .get("MultithreadingJobConfig")
                .start(migrationPersonStep)
                .next(migrationBankDataStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
