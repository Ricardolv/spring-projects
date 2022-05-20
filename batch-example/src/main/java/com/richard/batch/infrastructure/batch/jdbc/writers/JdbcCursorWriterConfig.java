package com.richard.batch.infrastructure.batch.jdbc.writers;

import com.richard.batch.domain.Client;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JdbcCursorWriterConfig {

    @Bean
    public ItemWriter<Client> jdbcCursorWriter() {
        return clients -> clients.forEach(System.out::println);
    }

}
