package com.richard.batch.infrastructure.batch.processors.script;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ScriptProcessorJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job scriptProcessorJob(Step scriptProcessorStep) {
        return jobBuilderFactory
                .get("scriptProcessorJob")
                .start(scriptProcessorStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
