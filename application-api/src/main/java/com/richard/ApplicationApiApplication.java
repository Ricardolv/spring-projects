package com.richard;

import com.richard.infrastructure.persistence.CourseEntity;
import com.richard.infrastructure.persistence.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationApiApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			CourseEntity c = new CourseEntity();
			c.setName("Angular com Spring");
			c.setCategory("front-end");

			courseRepository.save(c);
		};
	}
}
