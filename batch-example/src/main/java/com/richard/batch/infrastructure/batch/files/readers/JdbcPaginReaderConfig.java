package com.richard.batch.infrastructure.batch.files.readers;

import com.richard.batch.domain.Client;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JdbcPaginReaderConfig {

    @Bean
    public JdbcPagingItemReader<Client> jdbcPaginReader(@Qualifier("appdatabase") DataSource dataSource,
                                                        PagingQueryProvider queryProvider) {
        return new JdbcPagingItemReaderBuilder<Client>()
                .name("jdbcPaginReader")
                .dataSource(dataSource)
                .queryProvider(queryProvider)
                .pageSize(1)
                .rowMapper(new BeanPropertyRowMapper<>(Client.class))
                .build();


    }

    @Bean
    public SqlPagingQueryProviderFactoryBean queryProvider(@Qualifier("appdatabase") DataSource dataSource) {
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("select *");
        queryProvider.setFromClause("from client");
        queryProvider.setSortKey("email");
        return queryProvider;
    }

}
