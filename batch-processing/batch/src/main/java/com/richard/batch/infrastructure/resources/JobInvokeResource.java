package com.richard.batch.infrastructure.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/job")
public class JobInvokeResource {

    private final Job processJobOne;
    private final Job processJobTwo;
    private final JobLauncher jobLauncher;

    @GetMapping(path = "/invoke")
    public ResponseEntity<String> invoke() {

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

        return ResponseEntity.ok("Job executado!");

    }

}
