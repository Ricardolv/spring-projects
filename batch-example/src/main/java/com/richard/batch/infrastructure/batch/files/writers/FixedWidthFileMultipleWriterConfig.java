package com.richard.batch.infrastructure.batch.files.writers;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FixedWidthFileMultipleWriterConfig {

    @Bean
    public ItemWriter fixedWidthFileMultipleWriter() {
       return itens -> itens.forEach(System.out::println);
    }

}
