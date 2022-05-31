package com.pizza.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProductOrderDto extends BaseEntityDto {

    private List<PizzaDto> pizzasDto;

    public ProductOrderDto(List<PizzaDto> pizzasDto) {
        this.pizzasDto = pizzasDto;
    }
}
