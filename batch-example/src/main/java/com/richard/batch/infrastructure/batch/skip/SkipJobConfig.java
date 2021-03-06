package com.richard.batch.infrastructure.batch.skip;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class SkipJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job skipJob(Step skipStep) {
        return jobBuilderFactory.get("skipJob")
                .start(skipStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
