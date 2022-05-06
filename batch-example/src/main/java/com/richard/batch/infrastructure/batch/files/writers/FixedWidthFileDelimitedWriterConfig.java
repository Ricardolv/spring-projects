package com.richard.batch.infrastructure.batch.files.writers;

import com.richard.batch.domain.Client;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FixedWidthFileDelimitedWriterConfig {

    @Bean
    public ItemWriter<Client> fixedWidthFileDelimitedWriter() {
       return itens -> itens.forEach(System.out::println);
//        return itens -> {
//            for (Client item : itens) {
//                if (item.getName().equals("Maria"))
//                    throw new Exception();
//                else
//                    System.out.println(item);
//            }
//        };
    }

}
