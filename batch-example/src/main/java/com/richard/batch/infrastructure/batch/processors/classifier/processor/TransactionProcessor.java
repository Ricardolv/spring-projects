package com.richard.batch.infrastructure.batch.processors.classifier.processor;

import com.richard.batch.domain.Transaction;
import org.springframework.batch.item.ItemProcessor;

public class TransactionProcessor implements ItemProcessor<Transaction, Transaction> {

    @Override
    public Transaction process(Transaction transaction) throws Exception {
        System.out.println(String.format("\nAplicando regras de negócio na transação %s ", transaction.getId()));
        return transaction;
    }

}
