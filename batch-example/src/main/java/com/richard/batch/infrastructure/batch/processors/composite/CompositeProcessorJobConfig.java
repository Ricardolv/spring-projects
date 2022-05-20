package com.richard.batch.infrastructure.batch.processors.composite;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class CompositeProcessorJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job compositeProcessorJob(Step compositeProcessorStep) {
        return jobBuilderFactory
                .get("compositeProcessorJob")
                .start(compositeProcessorStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
