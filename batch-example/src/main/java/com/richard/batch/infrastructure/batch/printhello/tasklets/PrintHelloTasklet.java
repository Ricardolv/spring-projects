package com.richard.batch.infrastructure.batch.printhello.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class PrintHelloTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Olá, mundo!");
        return RepeatStatus.FINISHED;
    }


//    @Bean
//    @StepScope
//    public Tasklet printHelloTasklet(@Value("#{jobParameters['name']}") String name) {
//        return (contribution, chunkContext) -> {
//            System.out.println(String.format("Olá, %s!", name));
//            return RepeatStatus.FINISHED;
//        };
//    }

}
