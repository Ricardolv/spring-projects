package com.richard.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableBatchProcessing
@SpringBootApplication
public class AsynchronousProcessingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AsynchronousProcessingApplication.class, args);
		context.close();
	}

}
