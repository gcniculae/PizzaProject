package com.pizza.converter;

import com.pizza.dto.PizzeriaDto;
import com.pizza.entity.Pizzeria;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PizzeriaConverter extends BaseConverter<PizzeriaDto, Pizzeria> {

    @Override
    public PizzeriaDto convertFromEntityToDto(Pizzeria pizzeria) {
        PizzeriaDto pizzeriaDto = new PizzeriaDto();
        BeanUtils.copyProperties(pizzeria, pizzeriaDto);

        return pizzeriaDto;
    }

    @Override
    public Pizzeria convertFromDtoToEntity(PizzeriaDto pizzeriaDto) {
        Pizzeria pizzeria = new Pizzeria();
        BeanUtils.copyProperties(pizzeria, pizzeriaDto);

        return pizzeria;
    }
}
