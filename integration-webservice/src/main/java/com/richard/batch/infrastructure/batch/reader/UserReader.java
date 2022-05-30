package com.richard.batch.infrastructure.batch.reader;

import com.richard.batch.domain.User;
import com.richard.batch.domain.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserReader implements ItemReader<User> {

    private final RestTemplate restTemplate = new RestTemplate();
    private int page = 1;

    @Override
    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }

    private List<User> fetchUserDataFromApi() {
        ResponseEntity<UserResponse> response = restTemplate.getForEntity(String.format("https://gorest.co.in/public/v2/users?page=%d", page), UserResponse.class);
        return response.getBody().getData();
    }

}
