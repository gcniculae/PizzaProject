package com.pizza.transformer;

import com.pizza.dto.PizzeriaDto;
import com.pizza.entity.Pizzeria;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PizzeriaTransformer {

    public PizzeriaDto transformFromPizzeriaToPizzeriaDto(Pizzeria pizzeria) {
        PizzeriaDto pizzeriaDto = new PizzeriaDto();
        BeanUtils.copyProperties(pizzeria, pizzeriaDto);

        return pizzeriaDto;
    }

    public Pizzeria transformFromPizzeriaDtoToPizzeria(PizzeriaDto pizzeriaDto) {
        Pizzeria pizzeria = new Pizzeria();
        BeanUtils.copyProperties(pizzeria, pizzeriaDto);

        return pizzeria;
    }
}
