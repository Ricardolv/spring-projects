package com.richard.batch.infrastructure.batch.printoddnumber.steps;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
public class PrintOddNumberStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
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
