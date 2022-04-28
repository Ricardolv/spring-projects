package com.richard.batch.infrastructure.batch.printoddnumber.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class PrintOddNumberJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job printOddNumberJob(Step printOddNumber) {
        return jobBuilderFactory.get("printOddNumberJob")
                .start(printOddNumber)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
