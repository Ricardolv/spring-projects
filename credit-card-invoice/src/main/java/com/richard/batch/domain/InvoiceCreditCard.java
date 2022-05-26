package com.richard.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class InvoiceCreditCard {

    private Client client;
    private CreditCard creditCard;
    private List<Transaction> transactions = new ArrayList<>();

    public Double getTotal() {
        return transactions
                .stream()
                .mapToDouble(Transaction::getValue)
                .reduce(0.0, Double::sum);
    }
}
