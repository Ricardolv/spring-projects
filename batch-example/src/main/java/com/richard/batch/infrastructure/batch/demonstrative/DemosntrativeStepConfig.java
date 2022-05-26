package com.richard.batch.infrastructure.batch.demonstrative;

import com.richard.batch.domain.GroupRelease;
import com.richard.batch.infrastructure.batch.demonstrative.reader.GroupReleaseReader;
import com.richard.batch.infrastructure.batch.demonstrative.writer.GroupReleaseFooter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class DemosntrativeStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step demonstrativeStep(GroupReleaseReader demonstrativeReader,
                                  ItemWriter<GroupRelease> demonstrativeWriter,
                                  GroupReleaseFooter footerCallBack) {
        return stepBuilderFactory
                .get("demonstrativeStep")
                .<GroupRelease, GroupRelease>chunk(100)
                .reader(demonstrativeReader)
                .writer(demonstrativeWriter)
                .listener(footerCallBack)
                .build();
    }

}
