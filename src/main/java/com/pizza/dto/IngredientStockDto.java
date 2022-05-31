package com.pizza.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class IngredientStockDto extends BaseEntityDto {

    private String name;
    private int quantity;
    private LocalDate expirationDate;
    private PizzeriaDto pizzeriaDto;

    public IngredientStockDto(String name, int quantity, LocalDate expirationDate, PizzeriaDto pizzeriaDto) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.pizzeriaDto = pizzeriaDto;
    }
}
