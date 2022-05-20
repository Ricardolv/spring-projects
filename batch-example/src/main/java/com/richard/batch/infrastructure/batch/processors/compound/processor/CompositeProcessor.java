package com.richard.batch.infrastructure.batch.processors.compound.processor;

import com.richard.batch.domain.ClientProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Configuration
public class CompositeProcessor {
    private Set<String> emails = new HashSet<>();

    @Bean
    public ItemProcessor<ClientProcessor, ClientProcessor> itemCompositeProcessor() throws Exception {
        return new CompositeItemProcessorBuilder<ClientProcessor, ClientProcessor>()
                .delegates(beanValidatingItemProcessor(), emailValidatingItemProcessor())
                .build();
    }

    private BeanValidatingItemProcessor<ClientProcessor> beanValidatingItemProcessor() throws Exception {
        BeanValidatingItemProcessor<ClientProcessor> processor = new BeanValidatingItemProcessor<>();
        processor.setFilter(true);
        processor.afterPropertiesSet();
        return processor;
    }

    private ValidatingItemProcessor<ClientProcessor> emailValidatingItemProcessor() {
        ValidatingItemProcessor<ClientProcessor> processor = new BeanValidatingItemProcessor<>();
        processor.setValidator(validator());
        processor.setFilter(true);
        return processor;
    }

    private Validator<ClientProcessor> validator() {
        return clientProcessor -> {
            if (emails.contains(clientProcessor.getEmail()))
                throw new ValidationException(String.format("O cliente %s já foi processado!", clientProcessor.getEmail()));
            emails.add(clientProcessor.getEmail());
        };
    }
}
