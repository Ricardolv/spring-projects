package com.richard.batch.application.datasources;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class AppDataSourceConfig {

    @Bean(name = "appDataSourceProperties")
    @ConfigurationProperties(prefix = "app.datasource")
    public DataSourceProperties appDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean(name = "appdatabase")
    @ConfigurationProperties(prefix = "app.datasource")
    public DataSource appDataSource(@Qualifier("appDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public PlatformTransactionManager transactionManagerApp(@Qualifier("appdatabase") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
