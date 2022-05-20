package com.richard.batch.infrastructure.batch.processors.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ValidationProcessorJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job validationProcessorJob(Step validationProcessorStep) {
        return jobBuilderFactory
                .get("validationProcessorJob")
                .start(validationProcessorStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
