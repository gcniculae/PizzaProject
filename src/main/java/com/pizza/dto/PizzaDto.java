package com.pizza.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PizzaDto extends BaseEntityDto {

    private String name;
    private ProductOrderDto orderDto;

    private double price;

    public PizzaDto(String name, ProductOrderDto orderDto, double price) {
        this.name = name;
        this.orderDto = orderDto;
        this.price = price;
    }
}
