package com.richard.batch.infrastructure.batch.step;

import com.richard.batch.domain.InvoiceCreditCard;
import com.richard.batch.domain.Transaction;
import com.richard.batch.infrastructure.batch.reader.InvoiceCreditCardReader;
import com.richard.batch.infrastructure.batch.writer.TotalTransactionFooterCallback;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class InvoiceCreditCardStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step invoiceCreditCardStep(ItemStreamReader<Transaction> readTransactionReader,
                                      ItemProcessor<InvoiceCreditCard, InvoiceCreditCard> loadinDataClientProcessor,
                                      ItemWriter<InvoiceCreditCard> invoiceCreditCardWriter,
                                      TotalTransactionFooterCallback listener) {
        return stepBuilderFactory
                .get("invoiceCreditCardStep")
                .<InvoiceCreditCard, InvoiceCreditCard>chunk(1)
                .reader(new InvoiceCreditCardReader(readTransactionReader))
                .processor(loadinDataClientProcessor)
                .writer(invoiceCreditCardWriter)
                .listener(listener)
                .build();
    }
}
