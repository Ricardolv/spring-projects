package com.richard.batch.insfrastructure.batch.person.writer;

import com.richard.batch.domain.Person;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Date;

@Configuration
public class PersonMigrationWriterConfig {

    @Bean
    public JdbcBatchItemWriter<Person> personMigrationWriter(@Qualifier("appdatabase") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Person>()
                .dataSource(dataSource)
                .sql("INSERT INTO pessoa (id, nome, email, data_nascimento, idade) VALUES (?, ?, ?, ?, ?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .build();

    }

    private ItemPreparedStatementSetter<Person> itemPreparedStatementSetter() {
        return (person, ps) -> {
            ps.setInt(1, person.getId());
            ps.setString(2, person.getName());
            ps.setString(3, person.getEmail());
            ps.setDate(4, new Date(person.getBirthDate().getTime()));
            ps.setInt(5, person.getAge());
        };
    }

}
