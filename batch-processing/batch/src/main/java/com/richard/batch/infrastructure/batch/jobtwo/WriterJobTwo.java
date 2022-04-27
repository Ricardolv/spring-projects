package com.richard.batch.infrastructure.batch.jobtwo;

import com.richard.batch.domain.BaseData;
import com.richard.batch.domain.BaseDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class WriterJobTwo implements ItemWriter<BaseData> {

    private final BaseDataService service;

    @Override
    public void write(List<? extends BaseData> list) throws Exception {

        list.forEach(service::save);

    }
}
