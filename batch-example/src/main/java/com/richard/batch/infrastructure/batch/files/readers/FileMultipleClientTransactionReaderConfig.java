package com.richard.batch.infrastructure.batch.files.readers;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class FileMultipleClientTransactionReaderConfig {

    @StepScope
    @Bean
    public MultiResourceItemReader fileMultipleClientTransactionReader(
            @Value("#{jobParameters['fileClients']}") Resource[] fileClients,
            FlatFileItemReader fixedWidthFileMultipleReader) {

        return new MultiResourceItemReaderBuilder<>()
                .name("fileMultipleClientTransactionReader")
                .resources(fileClients)
                .delegate(new FileMultipleClientTransactionReader(fixedWidthFileMultipleReader))
                .build();


    }

}
