package com.richard.batch.infrastructure.batch.files.readers;

import com.richard.batch.domain.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class FixedWidthFileMultipleReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Client> fixedWidthFileMultipleReader(
            @Value("#{jobParameters['fileClientsFileMultiple']}") Resource fileClientsFileMultiple, LineMapper lineMapper) {
        return new FlatFileItemReaderBuilder<Client>()
                .name("fixedWidthFileMultipleReader")
                .resource(fileClientsFileMultiple)
                .lineMapper(lineMapper)
                .build();
    }

}
