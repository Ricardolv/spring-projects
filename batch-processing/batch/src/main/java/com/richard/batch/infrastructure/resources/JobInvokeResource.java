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

    private final Job processJob;
    private final JobLauncher jobLauncher;

    @GetMapping(path = "/invoke")
    public ResponseEntity<String> invoke() {

        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        try {
            jobLauncher.run(processJob, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ResponseEntity.ok("Job executado!");

    }

}
