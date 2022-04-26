package com.richard.ms3.infrastructure.resources.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BaseData {

    private String nome;
    private String descricao;
    private String email;
    private String senha;

}
