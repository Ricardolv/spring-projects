package com.richard.batch.infrastructure.batch.person.step;

import com.richard.batch.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class PersonMigrationStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step personMigrationStep(ItemReader<Person> filePersonReader,
                                    ItemWriter<Person> personWriter) {
        return stepBuilderFactory
                .get("personMigrationStep")
                .<Person, Person>chunk(1)
                .reader(filePersonReader)
                .writer(personWriter)
                .build();
    }

}
