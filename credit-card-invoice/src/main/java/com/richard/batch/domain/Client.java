package com.richard.batch.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class Client {

    private int id;

    @JsonProperty(value = "nome")
    private String name;

    @JsonProperty(value = "endereco")
    private String address;
}
