package com.richard.batch.infrastructure.batch.person.step;


import com.richard.batch.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
public class MigrationPersonStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Qualifier("transactionManagerApp")
    private final PlatformTransactionManager transactionManagerApp;

    @Bean
    public Step migrationPersonStep(
            ItemReader<Person> filePersonReader,
            AsyncItemWriter<Person> asyncPersonMigrationWriter,
            AsyncItemProcessor<Person, Person> asyncPersonProcessor) {
        return ((SimpleStepBuilder<Person, Person>) stepBuilderFactory
                .get("migrationPersonStep")
                .<Person, Person>chunk(1000)
                .reader(filePersonReader)
                .processor((ItemProcessor) asyncPersonProcessor)
                .writer(asyncPersonMigrationWriter)
                .transactionManager(transactionManagerApp))
                .build();
    }
}
