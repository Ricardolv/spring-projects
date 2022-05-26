package com.richard.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class Person {

    private int id;
    private String name;
    private String email;
    private Date birthDate;
    private int age;

    public boolean isValid() {
        return isNotBlank(name) && isNotBlank(email) && nonNull(birthDate);
    }
}
