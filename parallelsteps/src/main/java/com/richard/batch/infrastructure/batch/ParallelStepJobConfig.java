package com.richard.batch.infrastructure.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@RequiredArgsConstructor
@Configuration
public class ParallelStepJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job parallelStepJob(@Qualifier("migrationPersonStep") Step migrationPersonStep,
                               @Qualifier("migrationBankDataStep") Step migrationBankDataStep) {
        return jobBuilderFactory
                .get("parallelStepJob")
                .start(stepsParallel(migrationPersonStep, migrationBankDataStep))
                .end()
                .incrementer(new RunIdIncrementer())
                .build();
    }

    private Flow stepsParallel(Step migrationPersonStep, Step migrationBankDataStep) {
        return new FlowBuilder<Flow>("stepsParallel")
                .start(migrationPersonFlow(migrationPersonStep))
                .split(new SimpleAsyncTaskExecutor())
                .add(migrationBankDataFlow(migrationBankDataStep))
                .build();
    }

    private Flow migrationPersonFlow(Step migrationPersonStep) {
        return new FlowBuilder<Flow>("migrationPersonFlow")
                .start(migrationPersonStep)
                .build();
    }

    private Flow migrationBankDataFlow(Step migrationBankDataStep) {
        return new FlowBuilder<Flow>("migrationBankDataFlow")
                .start(migrationBankDataStep)
                .build();
    }

}
