package com.richard.ms2.domain;

import com.richard.ms2.infrastructure.persistence.entities.BaseDataThree;
import com.richard.ms2.infrastructure.persistence.entities.BaseDataTwo;
import com.richard.ms2.infrastructure.persistence.repositories.BaseDataThreeRepository;
import com.richard.ms2.infrastructure.persistence.repositories.BaseDataTwoRepository;
import com.richard.ms2.infrastructure.resources.request.BaseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BaseDataService {

    private final BaseDataTwoRepository baseDataTwoRepository;
    private final BaseDataThreeRepository baseDataThreeRepository;

    public void save(final BaseData baseData) {

        log.info("{}.save() - INICIO.", this.getClass().getName());

        Optional<BaseDataTwo> baseDataTwoOptional = baseDataTwoRepository.findByNomeAndDescricao(baseData.getNome(),
                                                                                                 baseData.getDescricao());

        if (baseDataTwoOptional.isEmpty()) {
            var baseDataTwo = BaseDataTwo.builder()
                    .nome(baseData.getNome())
                    .descricao(baseData.getDescricao())
                    .build();

            baseDataTwoRepository.save(baseDataTwo);

        }

        Optional<BaseDataThree> baseDataThreeOptional = baseDataThreeRepository.findByEmailAndSenha(baseData.getEmail(),
                                                                                                    baseData.getSenha());

        if (baseDataThreeOptional.isEmpty()) {
            var baseDataThree = BaseDataThree.builder()
                    .email(baseData.getEmail())
                    .senha(baseData.getSenha())
                    .build();

            baseDataThreeRepository.save(baseDataThree);
        }

        log.info("{}.save() - FIM.", this.getClass().getName());
    }
}
