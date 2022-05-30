package com.richard.batch.infrastructure.batch.person.processor;

import com.richard.batch.domain.Person;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class PersonProcessorConfig {
    private static final RestTemplate restTemplate = new RestTemplate();

    @Bean
    public AsyncItemProcessor<Person, Person> asyncPersonProcessor() {
        AsyncItemProcessor<Person, Person> processor = new AsyncItemProcessor<>();
        processor.setDelegate(personProcessorDelegate());
        processor.setTaskExecutor(taskExecutor());
        return processor;
    }

    private ItemProcessor personProcessorDelegate() {
       return (ItemProcessor<Person, Person>) person -> {
           try {
               String uri = String.format("http://my-json-server.typicode.com/giuliana-bezerra/demo/profile/%d",
                                          person.getId());
               ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
           } catch (RestClientResponseException e) {
               System.out.println(person.getId());
               return person;
           }
           return person;
       };
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(8);
        executor.setThreadNamePrefix("Multithread-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }


}
