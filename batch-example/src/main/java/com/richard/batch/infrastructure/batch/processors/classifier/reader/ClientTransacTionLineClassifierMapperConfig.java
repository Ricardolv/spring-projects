package com.richard.batch.infrastructure.batch.processors.classifier.reader;

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
public class ClientTransacTionLineClassifierMapperConfig {

    @Bean
    public PatternMatchingCompositeLineMapper lineClassifierMapper() {
        // Esses transformam a linha em palavras
        Map<String, LineTokenizer> tokenizers = new HashMap<>();
        tokenizers.put("0*", clientLineTokenizer());
        tokenizers.put("1*", transactionLineTokenizer());

        // Esses mapeiam as palavras num objeto de domínio
        Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>(2);
        fieldSetMappers.put("0*", fieldSetMapper(Client.class));
        fieldSetMappers.put("1*", fieldSetMapper(Transaction.class));

        // Esse mapeador de linha do framework utiliza padrões para decidir qual lógica
        // de mapeamento será executada.
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper<>();
        lineMapper.setTokenizers(tokenizers);
        lineMapper.setFieldSetMappers(fieldSetMappers);
        return lineMapper;
    }

    private LineTokenizer clientLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("name", "lastName", "age", "email");
        lineTokenizer.setIncludedFields(1, 2, 3, 4);
        return lineTokenizer;
    }

    private LineTokenizer transactionLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("id", "description", "value");
        lineTokenizer.setIncludedFields(1, 2, 3);
        return lineTokenizer;
    }

    private FieldSetMapper fieldSetMapper(Class classe) {
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(classe);
        return fieldSetMapper;
    }

}
