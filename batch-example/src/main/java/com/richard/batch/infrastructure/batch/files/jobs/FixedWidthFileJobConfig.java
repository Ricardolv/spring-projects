package com.richard.batch.infrastructure.batch.files.jobs;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FixedWidthFileJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job fixedWidthFileJob(Step fixedWidthFileStep) {
        return jobBuilderFactory
                .get("fixedWidthFileJob")
                .start(fixedWidthFileStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
