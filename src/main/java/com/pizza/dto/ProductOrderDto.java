package com.pizza.dto;

import com.pizza.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductOrderDto extends BaseEntityDto {

    private Client client;
    private List<PizzaDto> pizzasDto;
}
