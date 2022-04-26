package com.richard.client.domain;

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
