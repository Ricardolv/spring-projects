package com.richard.batch.domain;

import com.richard.batch.infrastructure.client.BaseDataClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BaseDataService {

    private final BaseDataClient client;

    public List<BaseData> getAll() {
        return client.getAll();
    }

    public void save(BaseData baseData) {
        client.save(baseData);
    }


    public BaseData apply(BaseData baseData) {
        return baseData;
    }


}
