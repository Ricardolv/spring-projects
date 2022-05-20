package com.richard.batch.infrastructure.batch.processors.classifier;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ClassifierProcessorJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job classifierProcessorJob(Step classifierProcessorStep) {
        return jobBuilderFactory
                .get("classifierProcessorJob")
                .start(classifierProcessorStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
