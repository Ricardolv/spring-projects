package com.richard.batch.infrastructure.batch.files.writers;

import com.richard.batch.domain.Client;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FixedWidthFileWriterConfig {

    @Bean
    public ItemWriter<Client> fixedWidthFileWriter() {
        return items -> items.forEach(System.out::println);
    }

}
