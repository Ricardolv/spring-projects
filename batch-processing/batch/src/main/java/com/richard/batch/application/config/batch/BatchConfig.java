package com.richard.batch.application.config.batch;

import com.richard.batch.application.config.batch.job.JobCompletionListener;
import com.richard.batch.domain.BaseData;
import com.richard.batch.infrastructure.batch.jobone.ProcessorJobOne;
import com.richard.batch.infrastructure.batch.jobone.ReaderJobOne;
import com.richard.batch.infrastructure.batch.jobone.WriterJobOne;
import com.richard.batch.infrastructure.batch.jobtwo.ProcessorJobTwo;
import com.richard.batch.infrastructure.batch.jobtwo.ReaderJobTwo;
import com.richard.batch.infrastructure.batch.jobtwo.WriterJobTwo;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@RequiredArgsConstructor
@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final ReaderJobOne readerJobOne;
    private final ProcessorJobOne processorJobOne;
    private final WriterJobOne writerJobOne;

    private final ReaderJobTwo readerJobTwo;
    private final ProcessorJobTwo processorJobTwo;
    private final WriterJobTwo writerJobTwo;

    @Primary
    @Bean
    public Job processJobOne() {
        return jobBuilderFactory.get("processJobOne")
                .incrementer(new RunIdIncrementer()).listener(listener())
                .flow(orderStepOne()).end().build();
    }

    @Bean
    public Step orderStepOne() {
        return stepBuilderFactory.get("orderStepOne").<BaseData, BaseData> chunk(2)
                .reader(readerJobOne)
                .processor(processorJobOne)
                .writer(writerJobOne)
                .build();
    }

    @Bean
    public Job processJobTwo() {
        return jobBuilderFactory.get("processJobTwo")
                .incrementer(new RunIdIncrementer()).listener(listener())
                .flow(orderStepTwo()).end().build();
    }

    @Bean
    public Step orderStepTwo() {
        return stepBuilderFactory.get("orderStepTwo").<BaseData, BaseData> chunk(2)
                .reader(readerJobTwo)
                .processor(processorJobTwo)
                .writer(writerJobTwo)
                .build();
    }

    private JobExecutionListener listener() {
        return new JobCompletionListener();
    }





}
