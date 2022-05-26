package com.richard.batch.infrastructure.batch.demonstrative.writer;

import com.richard.batch.domain.GroupRelease;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;


@Configuration
public class DemosntrativeWriterConfig {

    @StepScope
    @Bean
    public FlatFileItemWriter<GroupRelease> demonstrativeWriter(
        @Value("#{jobParameters['fileClientsDemosntrativeExit']}") Resource fileClientsDemosntrativeExit,
        GroupReleaseFooter groupReleaseFooter) {
        return new FlatFileItemWriterBuilder<GroupRelease>()
                .name("demonstrativeWriter")
                .resource(fileClientsDemosntrativeExit)
                .lineAggregator(lineAgregator())
                .headerCallback(headerCallback())
                .footerCallback(groupReleaseFooter)
                .build();
    }

    private LineAggregator<GroupRelease> lineAgregator() {
        return groupRelease -> {
            var formatGroupRelease =  format("[%d] %s - %s\n", groupRelease.getCodeNatureExpense(),
                                                            groupRelease.getDescriptionNatureExpense(),
                                                            NumberFormat.getCurrencyInstance().format(groupRelease.getTotal()));
            StringBuilder strBuilder = new StringBuilder();
            groupRelease.getReleases().forEach(release ->
                    strBuilder.append(format("\t [%s] %s - %s\n", new SimpleDateFormat("dd/MM/yyyy")
                                      .format(release.getDate()), release.getDescription(),
                                      NumberFormat.getCurrencyInstance().format(release.getValue()))));

            var formatRelease = strBuilder.toString();
            return formatGroupRelease + formatRelease;
        };
    }

    private FlatFileHeaderCallback headerCallback() {
        return writer -> {
            writer.append(format("SISTEMA INTEGRADO: XPTO \t\t\t\t DATA: %s\n", new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
            writer.append(format("MÓDULO: ORÇAMENTO \t\t\t\t\t\t HORA: %s\n", new SimpleDateFormat("HH:MM").format(new Date())));
            writer.append(format("\t\t\tDEMONSTRATIVO ORCAMENTARIO\n"));
            writer.append(format("----------------------------------------------------------------------------\n"));
            writer.append(format("CODIGO NOME VALOR\n"));
            writer.append(format("\t Data Descricao Valor\n"));
            writer.append(format("----------------------------------------------------------------------------\n"));
        };
    }

}
