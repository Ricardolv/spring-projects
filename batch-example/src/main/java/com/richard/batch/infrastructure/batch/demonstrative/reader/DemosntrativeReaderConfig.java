package com.richard.batch.infrastructure.batch.demonstrative.reader;

import com.richard.batch.domain.GroupRelease;
import com.richard.batch.domain.Release;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

@Configuration
public class DemosntrativeReaderConfig {

    @Bean
    public JdbcCursorItemReader<GroupRelease> demosntrativeReader(
            @Qualifier("appdatabase") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<GroupRelease>()
                .name("demosntrativeReader")
                .dataSource(dataSource)
                .sql("select * from releases")
                .rowMapper(rowMapper())
                .build();
    }

    private RowMapper<GroupRelease> rowMapper() {
        return (rs, rowNum) -> {
            GroupRelease grupo = new GroupRelease();
            grupo.setCodeNatureExpense(rs.getInt("codeNatureExpense"));
            grupo.setDescriptionNatureExpense(rs.getString("descriptionNatureExpense"));
            grupo.setReleaseTmp(new Release());
            grupo.getReleaseTmp().setDate(rs.getDate("releaseDate"));
            grupo.getReleaseTmp().setDescription(rs.getString("releaseDescription"));
            grupo.getReleaseTmp().setValue(rs.getDouble("releaseValue"));
            return grupo;
        };
    }

}
