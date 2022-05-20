package com.richard.batch.infrastructure.batch.processors.classifier.writer;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ClassifierProcessorWriter {

    @Bean
    public ItemWriter itemClassifierProcessorWriter() {
        return items -> items.forEach(System.out::println);
    }

}
