package com.richard.batch.insfrastructure.batch.person.processor;

import com.richard.batch.domain.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class PersonProcessor implements ItemProcessor<Person, Person> {
    private static final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Person process(Person person) throws Exception {
        try {
            String uri = String.format("http://my-json-server.typicode.com/giuliana-bezerra/demo/profile/%d", person.getId());
            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        } catch (RestClientResponseException e) {
            System.out.println(person.getId());
            return person;
        }
        return person;
    }
}
