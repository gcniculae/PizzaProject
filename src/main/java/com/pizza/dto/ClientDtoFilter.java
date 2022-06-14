package com.pizza.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDtoFilter {

    private String fieldName;
    private String operand;
    private SpecificationOperation operator;
}
