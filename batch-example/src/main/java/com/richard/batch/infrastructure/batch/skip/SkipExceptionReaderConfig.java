package com.richard.batch.infrastructure.batch.skip;

import com.richard.batch.domain.Client;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class SkipExceptionReaderConfig {

    @Bean
    public ItemReader<Client> skipExceptionReader(@Qualifier("appdatabase") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Client>()
                .name("skipExceptionReader")
                .dataSource(dataSource)
                .sql("select * from client")
                .rowMapper(rowMapper())
                .build();
    }

    private RowMapper<Client> rowMapper() {
        return new RowMapper<Client>() {

            @Override
            public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
                if (rs.getRow() == 11)
                    throw new SQLException(String.format("Encerrando a execução - Cliente inválido %s", rs.getString("email")));
                else return clientRowMapper(rs);
            }

            private Client clientRowMapper(ResultSet rs) throws SQLException {
                var client = new Client();
                client.setName(rs.getString("name"));
                client.setLastName(rs.getString("lastName"));
                client.setAge(rs.getString("age"));
                client.setEmail(rs.getString("email"));
                return client;
            }
        };

    }

}
