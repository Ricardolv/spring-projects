package com.richard.batch.infrastructure.batch.jdbc.readers;

import com.richard.batch.domain.Client;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JdbcCursorReaderConfig {

    @Bean
    public JdbcCursorItemReader<Client> jdbcCursorReader(@Qualifier("appdatabase") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Client>()
                .name("jdbcCursorReader")
                .dataSource(dataSource)
                .sql("select * from client")
                .rowMapper(new BeanPropertyRowMapper<>(Client.class))
                .build();

    }
}
