package com.richard.batch.infrastructure.batch.bankdata.reader;

import com.richard.batch.domain.BankData;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@RequiredArgsConstructor
@Configuration
public class FileBankDataReaderConfig {

    @Bean
    public FlatFileItemReader<BankData> fileBankDataReader() {
        return new FlatFileItemReaderBuilder<BankData>()
                .name("fileBankDataReader")
                .resource(new FileSystemResource("files/dados_bancarios.csv"))
                .delimited()
                .names("personId", "agency", "account", "bank", "id")
                .addComment("--")
                .targetType(BankData.class)
                .build();
    }

}
