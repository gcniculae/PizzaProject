package com.pizza.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReservationDto extends BaseEntityDto {

    private PizzeriaDto pizzeriaDto;

    public ReservationDto(PizzeriaDto pizzeriaDto) {
        this.pizzeriaDto = pizzeriaDto;
    }
}
