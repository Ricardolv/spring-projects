package com.richard.batch.infrastructure.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@EnableBatchProcessing
@RequiredArgsConstructor
@Configuration
public class DataMigrationJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job dataMigrationJob(@Qualifier("personMigrationStep") Step personMigrationStep,
                                @Qualifier("bankDataMigrationStep") Step bankDataMigrationStep) {
        return jobBuilderFactory
                .get("dataMigrationJob")
                .start(stepsParallels(personMigrationStep, bankDataMigrationStep))
                .end()
                .incrementer(new RunIdIncrementer())
                .build();
    }

    private Flow stepsParallels(Step personMigrationStep, Step bankDataMigrationStep) {

        var bankDataMigrationFlow = new FlowBuilder<Flow>("bankDataMigrationFlow")
                .start(bankDataMigrationStep)
                .build();

        var stepsParallels = new FlowBuilder<Flow>("stepsParallelsFlow")
                .start(personMigrationStep)
                .split(new SimpleAsyncTaskExecutor())
                .add(bankDataMigrationFlow)
                .build();

        return stepsParallels;
    }

}
