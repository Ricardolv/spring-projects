package com.richard.batch.infrastructure.batch.reader;

import com.richard.batch.domain.Client;
import com.richard.batch.domain.CreditCard;
import com.richard.batch.domain.Transaction;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

@Configuration
public class TransactionReaderConfig {

    @Bean
    public JdbcCursorItemReader<Transaction> readTransactionReader(
            @Qualifier("appdatabase") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Transaction>()
                .name("readTransactionReader")
                .dataSource(dataSource)
                .sql("select * from transacao join cartao_credito using(numero_cartao_credito) order by numero_cartao_credito")
                .rowMapper(rowMapperTransaction())
                .build();
    }

    private RowMapper<Transaction> rowMapperTransaction() {
        return (rs, rowNum) -> {
            CreditCard creditCard = new CreditCard();
            creditCard.setNumberCardCredit(rs.getInt("numero_cartao_credito"));

            Client client = new Client();
            client.setId(rs.getInt("cliente"));
            creditCard.setClient(client);

            Transaction transaction = new Transaction();
            transaction.setId(rs.getInt("id"));
            transaction.setCreditCard(creditCard);
            transaction.setDate(rs.getDate("data"));
            transaction.setValue(rs.getDouble("valor"));
            transaction.setDescription(rs.getString("descricao"));

            return transaction;
        };
    }
}
