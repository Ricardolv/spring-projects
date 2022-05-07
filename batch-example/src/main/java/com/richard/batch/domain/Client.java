package com.richard.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class Client {

    private String name;
    private String lastName;
    private String age;
    private String email;
    private List<Transaction> transactions = new ArrayList<>();

}
