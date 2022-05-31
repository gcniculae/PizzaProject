package com.pizza.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LocationDto extends BaseEntityDto {

    private String name;
    private PizzeriaDto pizzeriaDto;

    public LocationDto(String name, PizzeriaDto pizzeriaDto) {
        this.name = name;
        this.pizzeriaDto = pizzeriaDto;
    }
}
