package com.richard.batch.infrastructure.batch.processors.classifier.processor;

import com.richard.batch.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ClassifierProcessor {

    @Bean
    public ItemProcessor itemClassifierProcessor() {
        return new ClassifierCompositeItemProcessorBuilder<>()
                .classifier(classifier())
                .build();
    }

    private Classifier classifier() {
        return (Classifier<Object, ItemProcessor>) object -> {
            if (object instanceof Client)
                return new ClientProcessor();
            else
                return new TransactionProcessor();
        };
    }

}
