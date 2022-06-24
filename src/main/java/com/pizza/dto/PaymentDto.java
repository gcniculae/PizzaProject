package com.pizza.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class PaymentDto {

    @NotNull
    private Long clientId;

    @NotNull
    private Double amount;
}
