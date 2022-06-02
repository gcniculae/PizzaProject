package com.pizza.transformer;

import com.pizza.dto.PizzaDto;
import com.pizza.entity.Pizza;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PizzaTransformer {

    public PizzaDto transformFromPizzaToPizzaDto(Pizza pizza) {
        PizzaDto pizzaDto = new PizzaDto();
        BeanUtils.copyProperties(pizza, pizzaDto);

        return pizzaDto;
    }

    public Pizza transformFromPizzaDtoToPizza(PizzaDto pizzaDto) {
        Pizza pizza = new Pizza();
        BeanUtils.copyProperties(pizzaDto, pizza);

        return pizza;
    }
}
