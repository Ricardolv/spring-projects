package com.richard.batch.infrastructure.batch.person.step;

import com.richard.batch.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class PersonMigrationStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step personMigrationStep(ItemReader<Person> filePersonReader,
                                    ClassifierCompositeItemWriter<Person> personClassifierWriter,
                                    FlatFileItemWriter<Person> filePersonMigrationInvalidWriter) {
        return stepBuilderFactory
                .get("personMigrationStep")
                .<Person, Person>chunk(1)
                .reader(filePersonReader)
                .writer(personClassifierWriter)
                .stream(filePersonMigrationInvalidWriter)
                .build();
    }

}
