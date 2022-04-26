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

    private final Job processJob;
    private final JobLauncher jobLauncher;

    @Scheduled(cron = "5 * * * * *")
    public void runBatchJob() {

        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        try {
            jobLauncher.run(processJob, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
