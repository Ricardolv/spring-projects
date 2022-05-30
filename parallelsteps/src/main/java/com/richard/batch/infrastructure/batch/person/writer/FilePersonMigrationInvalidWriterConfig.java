package com.richard.batch.infrastructure.batch.person.writer;

import com.richard.batch.domain.Person;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class FilePersonMigrationInvalidWriterConfig {

    @Bean
    public FlatFileItemWriter<Person> filePersonMigrationInvalidWriter() {
        return new FlatFileItemWriterBuilder<Person>()
                .name("filePersonMigrationInvalidWriter")
                .resource(new FileSystemResource("files/pessoas_invalidas.csv"))
                .delimited()
                .names("id")
                .build();

    }

}
