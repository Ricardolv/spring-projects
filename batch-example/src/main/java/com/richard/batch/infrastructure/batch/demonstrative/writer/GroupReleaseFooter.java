package com.richard.batch.infrastructure.batch.demonstrative.writer;

import com.richard.batch.domain.GroupRelease;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.List;

@Component
public class GroupReleaseFooter implements FlatFileFooterCallback {

    private Double totalGeneral = 0.0;

    @Override
    public void writeFooter(Writer writer) throws IOException {
        writer.append("\n");
        writer.append(String.format("\t\t\t\t\t\t\t  Total: %s", NumberFormat.getCurrencyInstance().format(totalGeneral)));
        writer.append(String.format("\t\t\t\t\t\t\t  Código de Autenticação: %s", "fkyew6868fewjfhjjewf"));
    }

    @BeforeWrite
    public void beforeWriter(List<GroupRelease> groupReleases) {
        groupReleases.forEach(groupRelease -> totalGeneral += groupRelease.getTotal());
    }



}
