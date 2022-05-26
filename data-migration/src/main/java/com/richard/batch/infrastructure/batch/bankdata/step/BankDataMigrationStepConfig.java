package com.richard.batch.infrastructure.batch.bankdata.step;

import com.richard.batch.domain.BankData;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class BankDataMigrationStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step bankDataMigrationStep(ItemReader<BankData> fileBankDataReader,
                                      ItemWriter<BankData> bankDataWriter) {
        return stepBuilderFactory
                .get("bankDataMigrationStep")
                .<BankData, BankData>chunk(1)
                .reader(fileBankDataReader)
                .writer(bankDataWriter)
                .build();
    }
}
