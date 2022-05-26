package com.richard.batch.infrastructure.batch.processor;

import com.richard.batch.domain.InvoiceCreditCard;
import com.richard.batch.infrastructure.client.SystemClient;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoadinDataClientProcessor implements ItemProcessor<InvoiceCreditCard, InvoiceCreditCard> {

    private final SystemClient systemClient;

    @Override
    public InvoiceCreditCard process(InvoiceCreditCard invoiceCreditCard) throws Exception {

        var client = systemClient.getClient(invoiceCreditCard.getClient().getId());
        invoiceCreditCard.setClient(client);
        return invoiceCreditCard;
    }
}
