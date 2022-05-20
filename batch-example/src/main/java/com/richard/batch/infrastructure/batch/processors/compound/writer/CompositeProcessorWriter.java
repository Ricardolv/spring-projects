package com.richard.batch.infrastructure.batch.processors.compound.writer;

import com.richard.batch.domain.ClientProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class CompositeProcessorWriter {

    @Bean
    public ItemWriter<ClientProcessor> itemCompositeProcessorWriter() {
        return clients -> clients.forEach(System.out::println);
    }

}
