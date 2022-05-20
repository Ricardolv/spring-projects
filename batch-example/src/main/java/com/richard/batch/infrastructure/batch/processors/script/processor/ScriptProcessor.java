package com.richard.batch.infrastructure.batch.processors.script.processor;

import com.richard.batch.domain.ClientProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ScriptItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ScriptProcessor {

    @Bean
    public ItemProcessor<ClientProcessor, ClientProcessor> itemScriptProcessor() {
        return new ScriptItemProcessorBuilder<ClientProcessor, ClientProcessor>()
                .language("nashorn")
                .scriptSource("var email = item.getEmail();" +
                              "var fileClientsScript = `ls | grep ${email}.txt`;" +
                              "if (!fileClientsScript) item; else null;")
                .build();
    }


}
