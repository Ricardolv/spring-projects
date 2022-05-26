package com.richard.batch.infrastructure.batch.person.writer;

import com.richard.batch.domain.Person;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonClassifierWriterConfig {

    @Bean
    public ClassifierCompositeItemWriter<Person> personClassifierWriter(
            JdbcBatchItemWriter<Person> personMigrationWriter,
            FlatFileItemWriter<Person> filePersonMigrationInvalidWriter) {
        return new ClassifierCompositeItemWriterBuilder<Person>()
                .classifier(classifier(personMigrationWriter, filePersonMigrationInvalidWriter))
                .build();

    }

    private Classifier<Person, ItemWriter<? super Person>> classifier(
            JdbcBatchItemWriter<Person> personMigrationWriter,
            FlatFileItemWriter<Person> filePersonMigrationInvalidWriter) {
        return person -> {
            if (person.isValid())
                return personMigrationWriter;
            else
                return filePersonMigrationInvalidWriter;
        };
    }
}
