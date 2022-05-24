package com.richard.batch.infrastructure.batch.written.jobs;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class WrittenFlatJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job writtenFlatJob(Step writtenFlatStep) {
        return jobBuilderFactory
                .get("writtenFlatJob")
                .start(writtenFlatStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
