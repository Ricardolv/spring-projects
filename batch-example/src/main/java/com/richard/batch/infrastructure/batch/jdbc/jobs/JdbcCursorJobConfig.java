package com.richard.batch.infrastructure.batch.jdbc.jobs;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class JdbcCursorJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job jdbcCursorJob(Step jdbcCursorStep) {
        return jobBuilderFactory
                .get("jdbcCursorJob")
                .start(jdbcCursorStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
