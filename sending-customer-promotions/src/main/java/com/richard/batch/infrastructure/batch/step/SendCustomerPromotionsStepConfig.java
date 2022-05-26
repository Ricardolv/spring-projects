package com.richard.batch.infrastructure.batch.step;

import com.richard.batch.domain.InterestProductCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@RequiredArgsConstructor
@Configuration
public class SendCustomerPromotionsStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step sendCustomerPromotionsStep(ItemReader<InterestProductCustomer> sendCustomerPromotionsReader,
                                           ItemProcessor<InterestProductCustomer, SimpleMailMessage> sendCustomerPromotionsProcessor,
                                           ItemWriter<SimpleMailMessage> sendCustomerPromotionsWriter) {
        return stepBuilderFactory
                .get("sendCustomerPromotionsStep")
                .<InterestProductCustomer, SimpleMailMessage>chunk(1)
                .reader(sendCustomerPromotionsReader)
                .processor(sendCustomerPromotionsProcessor)
                .writer(sendCustomerPromotionsWriter)
                .build();


    }
}
