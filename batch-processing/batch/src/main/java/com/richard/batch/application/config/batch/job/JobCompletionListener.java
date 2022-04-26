package com.richard.batch.application.config.batch.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

@Slf4j
public class JobCompletionListener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution execution) {

        if (BatchStatus.COMPLETED.equals(execution.getStatus())) {
            log.info("JOB COMPLETED");
        }

    }
}
