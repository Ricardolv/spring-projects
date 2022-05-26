package com.richard.batch.infrastructure.batch.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@RequiredArgsConstructor
@Configuration
public class InvoiceCreditCardJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job invoiceCreditCardJob(Step invoiceCreditCardStep) {
        return jobBuilderFactory
                .get("invoiceCreditCardStep")
                .start(invoiceCreditCardStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
