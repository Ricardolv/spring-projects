package com.richard.batch.infrastructure.batch.person.reader;

import com.richard.batch.domain.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.Date;

@Configuration
public class FilePersonReaderConfig {

    @Bean
    public FlatFileItemReader<Person> filePersonReader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("filePersonReader")
                .resource(new FileSystemResource("files/pessoas.csv"))
                .delimited()
                .names("name", "email", "birthDate", "age", "id")
                .addComment("--")
                .fieldSetMapper(fieldSetMapper())
                .saveState(false)
                .build();
    }

    private FieldSetMapper<Person> fieldSetMapper() {
        return fieldSet -> {
            var person = new Person();
            person.setName(fieldSet.readString("name"));
            person.setEmail(fieldSet.readString("email"));
            person.setBirthDate(new Date(fieldSet.readDate("birthDate", "yyyy-MM-dd HH:mm:ss").getTime()));
            person.setAge(fieldSet.readInt("age"));
            person.setId(fieldSet.readInt("id"));
            return person;
        };
    }

}
