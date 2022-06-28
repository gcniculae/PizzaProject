package com.pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductOrderDto extends BaseEntityDto {

    @NotNull
    private Long clientId;

    @NotNull
    private List<Long> pizzasIds;

    private Long paymentId;
}
