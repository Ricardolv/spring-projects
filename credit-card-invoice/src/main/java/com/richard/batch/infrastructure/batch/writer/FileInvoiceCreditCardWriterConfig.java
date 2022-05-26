package com.richard.batch.infrastructure.batch.writer;

import com.richard.batch.domain.InvoiceCreditCard;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.ResourceSuffixCreator;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

@Configuration
public class FileInvoiceCreditCardWriterConfig {

    @Bean
    public MultiResourceItemWriter<InvoiceCreditCard> filesInvoiceCreditCard() {
        return new MultiResourceItemWriterBuilder<InvoiceCreditCard>()
                .name("filesInvoiceCreditCard")
                .resource(new FileSystemResource("files/fatura"))
                .itemCountLimitPerResource(1)
                .resourceSuffixCreator(suffixCreator())
                .delegate(fileInvoiceCreditCard())
                .build();
    }

    private ResourceSuffixCreator suffixCreator() {
        return index -> index + ".txt";
    }

    private FlatFileItemWriter<InvoiceCreditCard> fileInvoiceCreditCard() {
        return new FlatFileItemWriterBuilder<InvoiceCreditCard>()
                .name("fileInvoiceCreditCard")
                .resource(new FileSystemResource("files/fatura.txt"))
                .lineAggregator(lineAggregator())
                .headerCallback(headerCallback())
                .footerCallback(footerCallback())
                .build();
    }

    private LineAggregator<InvoiceCreditCard> lineAggregator() {
        return invoiceCreditCard -> {
            StringBuilder writer = new StringBuilder();
            writer.append(String.format("Nome: %s\n", invoiceCreditCard.getClient().getName()));
            writer.append(String.format("Endereço: %s\n\n\n", invoiceCreditCard.getClient().getAddress()));
            writer.append(String.format("Fatura completa do cartão %d\n", invoiceCreditCard.getCreditCard().getNumberCardCredit()));
            writer.append("-------------------------------------------------------------------------------------------------------------------------\n");
            writer.append("DATA DESCRICAO VALOR\n");
            writer.append("-------------------------------------------------------------------------------------------------------------------------\n");

            invoiceCreditCard.getTransactions().forEach(transaction -> writer.append(String.format("\n[%10s] %-80s - %s",
                    new SimpleDateFormat("dd/MM/yyyy").format(transaction.getDate()),
                    transaction.getDescription(),
                    NumberFormat.getCurrencyInstance().format(transaction.getValue())))
            );

            return writer.toString();
        };
    }

    private FlatFileHeaderCallback headerCallback() {
        return writer -> {
            writer.append(String.format("%121s\n", "Cartão XPTO"));
            writer.append(String.format("%121s\n\n", "Rua Vergueiro, 131"));
        };
    }

    @Bean
    public FlatFileFooterCallback footerCallback() {
        return new TotalTransactionFooterCallback();
    }

}
