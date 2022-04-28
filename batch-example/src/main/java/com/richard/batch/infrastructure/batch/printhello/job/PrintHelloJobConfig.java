package com.richard.batch.infrastructure.batch.printhello.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class PrintHelloJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job printHelloJob(Step printHelloStep) {
        return jobBuilderFactory
                .get("printHelloJob")
                .start(printHelloStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
