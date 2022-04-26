package com.richard.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class BaseData {

    private long id;
    private String nome;
    private String descricao;
    private String email;
    private String senha;

}
