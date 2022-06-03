package com.pizza.converter;

import com.pizza.dto.PizzaDto;
import com.pizza.entity.Pizza;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PizzaConverter extends BaseConverter<PizzaDto, Pizza> {

    @Override
    public PizzaDto transformFromEntityToDto(Pizza pizza) {
        PizzaDto pizzaDto = new PizzaDto();
        BeanUtils.copyProperties(pizza, pizzaDto);

        return pizzaDto;
    }

    @Override
    public Pizza transformFromDtoToEntity(PizzaDto pizzaDto) {
        Pizza pizza = new Pizza();
        BeanUtils.copyProperties(pizzaDto, pizza);

        return pizza;
    }
}
