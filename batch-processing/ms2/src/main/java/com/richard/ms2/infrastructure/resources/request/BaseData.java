package com.richard.ms2.infrastructure.resources.request;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class BaseData {

    private String nome;
    private String descricao;
    private String email;
    private String senha;

}
