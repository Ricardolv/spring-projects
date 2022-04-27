package com.richard.batch.infrastructure.schedules;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Configuration
public class BatchScheduled {

    private final Job processJobOne;
    private final Job processJobTwo;
    private final JobLauncher jobLauncher;

    @Scheduled(cron = "5 * * * * *")
    public void runBatchJob() {

        JobParameters paramsJobOne = new JobParametersBuilder()
                .addLong("timeJobOne", System.currentTimeMillis())
                .toJobParameters();

        JobParameters paramsJobTwo = new JobParametersBuilder()
                .addLong("timeJobTwo", System.currentTimeMillis())
                .toJobParameters();

        try {
            jobLauncher.run(processJobOne, paramsJobOne);
            jobLauncher.run(processJobTwo, paramsJobTwo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
