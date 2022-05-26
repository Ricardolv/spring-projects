package com.richard.batch.infrastructure.batch.reader;

import com.richard.batch.domain.InvoiceCreditCard;
import com.richard.batch.domain.Transaction;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class InvoiceCreditCardReader implements ItemStreamReader<InvoiceCreditCard> {
    private ItemStreamReader<Transaction> delegate;
    private Transaction currentTransaction;

    public InvoiceCreditCardReader(ItemStreamReader<Transaction> delegate) {
        this.delegate = delegate;
    }

    @Override
    public InvoiceCreditCard read() throws Exception {

        if (isNull(currentTransaction))
            currentTransaction = delegate.read();

        InvoiceCreditCard invoiceCreditCard = null;
        Transaction transaction = currentTransaction;

        if (nonNull(transaction)) {
            invoiceCreditCard = new InvoiceCreditCard();
            invoiceCreditCard.setCreditCard(transaction.getCreditCard());
            invoiceCreditCard.setClient(transaction.getCreditCard().getClient());
            invoiceCreditCard.getTransactions().add(transaction);

            while (isTransactionRelated(transaction))
                invoiceCreditCard.getTransactions().add(currentTransaction);
        }

        return invoiceCreditCard;
    }

    private boolean isTransactionRelated(Transaction transaction) throws Exception {
        return nonNull(peek())
                && transaction.getCreditCard().getNumberCardCredit() == currentTransaction.getCreditCard().getNumberCardCredit();
    }

    private Transaction peek() throws Exception {
        currentTransaction = delegate.read();
        return currentTransaction;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        this.delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        this.delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        this.delegate.close();
    }



}
