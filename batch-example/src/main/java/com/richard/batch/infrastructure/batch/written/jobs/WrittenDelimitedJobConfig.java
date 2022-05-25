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
public class WrittenDelimitedJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job writtenDelemitedJob(Step writtenDelimitedStep) {
        return jobBuilderFactory
                .get("writtenDelemitedJob")
                .start(writtenDelimitedStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
