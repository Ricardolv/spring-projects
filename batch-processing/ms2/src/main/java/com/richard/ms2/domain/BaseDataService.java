package com.richard.ms2.domain;

import com.richard.ms2.infrastructure.persistence.entities.BaseDataThree;
import com.richard.ms2.infrastructure.persistence.entities.BaseDataTwo;
import com.richard.ms2.infrastructure.persistence.repositories.BaseDataThreeRepository;
import com.richard.ms2.infrastructure.persistence.repositories.BaseDataTwoRepository;
import com.richard.ms2.infrastructure.resources.request.BaseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BaseDataService {

    private final BaseDataTwoRepository baseDataTwoRepository;
    private final BaseDataThreeRepository baseDataThreeRepository;

    public void save(final BaseData baseData) {

        var baseDataTwo = BaseDataTwo.builder()
                .nome(baseData.getNome())
                .descricao(baseData.getDescricao())
                .build();

        var baseDataThree = BaseDataThree.builder()
                .email(baseData.getEmail())
                .senha(baseData.getSenha())
                .build();

        baseDataTwoRepository.save(baseDataTwo);
        baseDataThreeRepository.save(baseDataThree);
    }
}
