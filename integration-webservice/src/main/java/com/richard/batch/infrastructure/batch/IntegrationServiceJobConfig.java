package com.richard.batch.infrastructure.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@EnableBatchProcessing
@Configuration
public class IntegrationServiceJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job job(Step integrationServiceStep) {
        return jobBuilderFactory
                .get("integrationServiceJob")
                .start(integrationServiceStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
