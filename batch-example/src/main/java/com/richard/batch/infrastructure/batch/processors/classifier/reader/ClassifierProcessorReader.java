package com.richard.batch.infrastructure.batch.processors.classifier.reader;

import com.richard.batch.domain.ClientProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@RequiredArgsConstructor
@Configuration
public class ClassifierProcessorReader {

    @StepScope
    @Bean
    public FlatFileItemReader itemClassifierProcessorReader(
            @Value("#{jobParameters['fileClientsClassifier']}") Resource fileClientsClassifier,
            LineMapper lineClassifierMapper) {
        return new FlatFileItemReaderBuilder<ClientProcessor>()
                .name("itemClassifierProcessorReader")
                .resource(fileClientsClassifier)
                .lineMapper(lineClassifierMapper)
                .build();
    }

}
