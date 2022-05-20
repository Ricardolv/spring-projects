package com.richard.batch.infrastructure.batch.skip;

import com.richard.batch.domain.Client;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SkipExceptionWriterConfig {

    @Bean
    public ItemWriter<Client> skipExceptionWriter() {
        return clients -> clients.forEach(System.out::println);
    }

}
