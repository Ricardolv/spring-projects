package com.richard.batch.infrastructure.batch.jobtwo;

import com.richard.batch.domain.BaseData;
import com.richard.batch.domain.BaseDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProcessorJobTwo implements ItemProcessor<BaseData, BaseData> {

    private final BaseDataService service;

    @Override
    public BaseData process(BaseData baseData) throws Exception {
        return service.apply(baseData);
    }
}
