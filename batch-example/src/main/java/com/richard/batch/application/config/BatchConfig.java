package com.richard.batch.application.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@EnableBatchProcessing
@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job printHelloJob() {
        return jobBuilderFactory
                .get("printHelloJob")
                .start(printHelloStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    public Step printHelloStep() {
        return stepBuilderFactory
                .get("printHelloStep")
                .tasklet(printHelloTasklet(null))
                .build();
    }

    @Bean
    @StepScope
    public Tasklet printHelloTasklet(@Value("#{jobParameters['name']}") String name) {
        return (contribution, chunkContext) -> {
            System.out.println(String.format("Olá, %s!", name));
            return RepeatStatus.FINISHED;
        };
    }



}
