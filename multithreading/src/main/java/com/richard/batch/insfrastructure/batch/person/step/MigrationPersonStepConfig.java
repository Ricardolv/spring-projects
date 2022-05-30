package com.richard.batch.insfrastructure.batch.person.step;


import com.richard.batch.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
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
            ClassifierCompositeItemWriter<Person> personClassifierWriter,
            ItemProcessor<Person, Person> personProcessor,
            FlatFileItemWriter<Person> filePersonInvalidWriter,
            TaskExecutor taskExecutor) {
        return stepBuilderFactory
                .get("migrationPersonStep")
                .<Person, Person>chunk(1000)
                .reader(filePersonReader)
                .writer(personClassifierWriter)
                .taskExecutor(taskExecutor)
                .stream(filePersonInvalidWriter)
                .transactionManager(transactionManagerApp)
                .build();
    }
}
