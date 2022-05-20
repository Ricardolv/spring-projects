package com.richard.batch.infrastructure.batch.processors.script;

import com.richard.batch.domain.ClientProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ScriptProcessorStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step scriptProcessorStep(ItemReader<ClientProcessor> itemScriptProcessorReader,
                                       ItemProcessor<ClientProcessor, ClientProcessor> itemScriptProcessor,
                                       ItemWriter<ClientProcessor> itemScriptProcessorWriter) {
        return stepBuilderFactory
                .get("scriptProcessorStep")
                .<ClientProcessor, ClientProcessor>chunk(1)
                .reader(itemScriptProcessorReader)
                .processor(itemScriptProcessor)
                .writer(itemScriptProcessorWriter)
                .build();
    }

}
