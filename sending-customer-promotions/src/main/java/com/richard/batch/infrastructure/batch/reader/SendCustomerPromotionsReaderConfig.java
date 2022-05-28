package com.richard.batch.infrastructure.batch.reader;

import com.richard.batch.domain.Client;
import com.richard.batch.domain.InterestProductCustomer;
import com.richard.batch.domain.Product;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

@Configuration
public class SendCustomerPromotionsReaderConfig {

    @Bean
    public JdbcCursorItemReader<InterestProductCustomer> sendCustomerPromotionsReader(
            @Qualifier("appdatabase") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<InterestProductCustomer>()
                .name("sendCustomerPromotionsReader")
                .dataSource(dataSource)
                .sql("select * from interesse_produto_cliente " +
                        "join cliente on (cliente = cliente.id) " +
                        "join produto on (produto = produto.id)")
                .rowMapper(rowMapper())
                .build();
    }

    private RowMapper<InterestProductCustomer> rowMapper() {
        return (rs, rowNum) -> {
            Client client = new Client();
            client.setId(rs.getInt("id"));
            client.setName(rs.getString("nome"));
            client.setEmail(rs.getString("email"));

            Product product = new Product();
            product.setId(rs.getInt(6));
            product.setName(rs.getString(7));
            product.setDescription(rs.getString("descricao"));
            product.setPrice(rs.getDouble("preco"));

            InterestProductCustomer interestProductCustomer = new InterestProductCustomer();
            interestProductCustomer.setClient(client);
            interestProductCustomer.setProduct(product);

            return interestProductCustomer;
        };
    }
}
