package com.richard.batch.infrastructure.batch.printhello.steps;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class PrintHelloStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step printHelloStep(Tasklet printHelloTasklet) {
        return stepBuilderFactory
                .get("printHelloStep")
                .tasklet(printHelloTasklet)
                .build();
    }

}
