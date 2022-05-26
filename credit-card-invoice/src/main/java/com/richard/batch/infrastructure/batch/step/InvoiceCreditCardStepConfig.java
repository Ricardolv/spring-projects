package com.richard.batch.infrastructure.batch.step;

import com.richard.batch.domain.InvoiceCreditCard;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class InvoiceCreditCardStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step invoiceCreditCardStep(ItemReader<InvoiceCreditCard> readTransactionReader,
                                      ItemProcessor<InvoiceCreditCard, InvoiceCreditCard> loadinDataClientProcessor,
                                      ItemWriter<InvoiceCreditCard> invoiceCreditCardWriter) {
        return stepBuilderFactory
                .get("invoiceCreditCardStep")
                .<InvoiceCreditCard, InvoiceCreditCard>chunk(1)
                .reader(readTransactionReader)
                .processor(loadinDataClientProcessor)
                .writer(invoiceCreditCardWriter)
                .build();
    }
}
