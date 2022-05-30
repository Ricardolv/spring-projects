package com.richard.batch.infrastructure.batch.reader;

import com.richard.batch.domain.User;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class UserReader implements ItemReader<User> {


    @Override
    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
