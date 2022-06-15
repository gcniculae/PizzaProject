package com.pizza.dto;

import com.pizza.repository.SpecificationOperation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDtoFilter {

    private String fieldName;
    private String operand;
    private SpecificationOperation operator;
    private Boolean isLocalDate;
}
