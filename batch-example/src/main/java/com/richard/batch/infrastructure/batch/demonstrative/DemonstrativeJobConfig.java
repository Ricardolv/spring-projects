package com.richard.batch.infrastructure.batch.demonstrative;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class DemonstrativeJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job demonstrativeJob(Step demonstrativeStep) {
        return jobBuilderFactory.get("demonstrativeJob")
                .start(demonstrativeStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
