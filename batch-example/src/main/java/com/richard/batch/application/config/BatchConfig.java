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
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

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

    @Bean
    public Job printOddNumberJob() {
        return jobBuilderFactory.get("printOddNumberJob")
                .start(printOddNumber())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    public Step printOddNumber() {
        return stepBuilderFactory.get("printOddNumber")
                .<Integer, String> chunk(10)
                .reader(countToTenReader())
                .processor(evenOrOddProcessor())
                .writer(printWriter())
                .build();
    }

    public IteratorItemReader<Integer> countToTenReader() {
        return new IteratorItemReader<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    public FunctionItemProcessor<Integer, String> evenOrOddProcessor() {
        return new FunctionItemProcessor<>(item -> item % 2 == 0 ?
                String.format("Item %s é par", item) : String.format("Item %s é impar", item));
    }

    public ItemWriter<? super String> printWriter() {
        return itens -> itens.forEach(System.out::println);
    }


}
