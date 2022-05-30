package com.richard.batch.infrastructure.batch.bankdata.writer;

import com.richard.batch.domain.BankData;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BankDataWriterConfig {

    @Bean
    public JdbcBatchItemWriter<BankData> bankDataWriter(@Qualifier("appdatabase") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<BankData>()
                .dataSource(dataSource)
                .sql("INSERT INTO dados_bancarios (id, pessoa_id, agencia, conta, banco) VALUES (:id, :personId, :agency, :account, :bank)")
                .beanMapped()
                .build();
    }
}
