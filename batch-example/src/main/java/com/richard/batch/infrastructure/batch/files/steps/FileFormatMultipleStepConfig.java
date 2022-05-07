package com.richard.batch.infrastructure.batch.files.steps;

import com.richard.batch.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FileFormatMultipleStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step fileFormatMultipleStep(MultiResourceItemReader<Client> fileMultipleClientTransactionReader,
                                       ItemWriter fixedWidthFileMultipleWriter) {
        return stepBuilderFactory
                .get("fileFormatMultipleStep")
                .<Client, Client>chunk(1)
                .reader(fileMultipleClientTransactionReader)
                .writer(fixedWidthFileMultipleWriter)
                .build();
    }
}
