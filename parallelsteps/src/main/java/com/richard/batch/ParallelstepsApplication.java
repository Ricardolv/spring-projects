package com.richard.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class ParallelstepsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParallelstepsApplication.class, args);
	}

}