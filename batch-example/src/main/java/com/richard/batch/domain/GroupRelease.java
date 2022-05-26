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
public class GroupRelease {

    private Integer codeNatureExpense;
    private String descriptionNatureExpense;
    private List<Release> releases = new ArrayList<>();
    private Release releaseTmp;

    public Double getTotal() {
        return releases
                .stream()
                .mapToDouble(Release::getValue)
                .reduce(0.0, Double::sum);
    }
}
