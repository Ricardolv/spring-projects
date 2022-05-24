package com.richard.batch.infrastructure.batch.written.writers;

import com.richard.batch.domain.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class WrittenFlatWriterConfig {

    @StepScope
    @Bean
    public FlatFileItemWriter<Client> writtenFlatWriter(
            @Value("#{jobParameters['fileClientsFlatExit']}") Resource fileClientsFlatExit) {
        return new FlatFileItemWriterBuilder<Client>()
                .name("writtenFlatWriter")
                .resource(fileClientsFlatExit)
                .formatted()
                .format("%-9s %-9s %-2s %-19s")
                .names("name", "lastName", "age", "email")
                .build();
    }

}
