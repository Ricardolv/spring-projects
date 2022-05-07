package com.richard.batch.infrastructure.batch.files.readers;

import com.richard.batch.domain.Client;
import com.richard.batch.domain.Transaction;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


public class ClientTransactionReader implements ItemStreamReader<Client> {
    private Object objectCurrent;
    private ItemStreamReader<Object> delegate;

    public ClientTransactionReader(ItemStreamReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Client read() throws Exception {

        if (isNull(objectCurrent))
            objectCurrent = delegate.read();

        Client client = (Client) objectCurrent;
        objectCurrent = null;

        if (nonNull(client)) {
            while (peek() instanceof Transaction)
                client.getTransactions().add((Transaction) objectCurrent);
        }

        return client;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }

    private Object peek() throws Exception {
        objectCurrent = delegate.read();
        return objectCurrent;
    }
}
