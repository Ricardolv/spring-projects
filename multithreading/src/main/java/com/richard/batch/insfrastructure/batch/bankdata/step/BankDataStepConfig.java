package com.richard.batch.insfrastructure.batch.bankdata.step;


import com.richard.batch.domain.BankData;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
public class BankDataStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Qualifier("transactionManagerApp")
    private final PlatformTransactionManager transactionManagerApp;

    @Bean
    public Step migrationBankDataStep(ItemReader<BankData> fileBankDataReader,
                                      ItemWriter<BankData> bankDataWriter,
                                      TaskExecutor taskExecutor) {
        return stepBuilderFactory
                .get("migrationBankDataStep")
                .<BankData, BankData>chunk(1000)
                .reader(fileBankDataReader)
                .writer(bankDataWriter)
                .taskExecutor(taskExecutor)
                .transactionManager(transactionManagerApp)
                .build();
    }
}
