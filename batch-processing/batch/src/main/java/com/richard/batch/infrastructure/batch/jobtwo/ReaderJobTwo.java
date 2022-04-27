package com.richard.batch.infrastructure.batch.jobtwo;

import com.richard.batch.domain.BaseData;
import com.richard.batch.domain.BaseDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReaderJobTwo implements ItemReader<BaseData> {

    private final BaseDataService service;
    private int count = 0;

    @Override
    public BaseData read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return concumerApi();
    }

    private BaseData concumerApi() {
        var dataList = this.service.getAllExternal();

        if (count < dataList.size())
            return dataList.get(count++);

        count = 0;
        return null;
    }
}

