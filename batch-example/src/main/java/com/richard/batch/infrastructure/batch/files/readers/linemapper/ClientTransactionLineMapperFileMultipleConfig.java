package com.richard.batch.infrastructure.batch.files.readers.linemapper;

import com.richard.batch.domain.Client;
import com.richard.batch.domain.Transaction;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ClientTransactionLineMapperFileMultipleConfig {

    @Bean
    public PatternMatchingCompositeLineMapper lineMapper() {
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper();
        lineMapper.setTokenizers(tokenizers());
        lineMapper.setFieldSetMappers(fieldSetMappers());
        return lineMapper;
    }

    private Map<String, LineTokenizer> tokenizers() {
        Map<String, LineTokenizer> tokenizers = new HashMap<>();
        tokenizers.put("0*", clientTokenizer());
        tokenizers.put("1*", transactionTokenizer());
        return tokenizers;
    }

    private LineTokenizer clientTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("name", "lastName", "age", "email");
        lineTokenizer.setIncludedFields(1, 2, 3, 4);
        return lineTokenizer;
    }

    private LineTokenizer transactionTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("id", "description", "value");
        lineTokenizer.setIncludedFields(1, 2, 3);
        return lineTokenizer;
    }

    private Map<String, FieldSetMapper> fieldSetMappers() {
        Map<String, FieldSetMapper> fieldSetMapperMappers = new HashMap<>();
        fieldSetMapperMappers.put("0*", fieldSetMapper(Client.class));
        fieldSetMapperMappers.put("1*", fieldSetMapper(Transaction.class));
        return fieldSetMapperMappers;
    }

    private FieldSetMapper fieldSetMapper(Class classWrapper) {
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(classWrapper);
        return fieldSetMapper;
    }

}
