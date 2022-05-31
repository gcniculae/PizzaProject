package com.pizza.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MenuDto extends BaseEntityDto {

    private PizzeriaDto pizzeriaDto;

    public MenuDto(PizzeriaDto pizzeriaDto) {
        this.pizzeriaDto = pizzeriaDto;
    }
}
