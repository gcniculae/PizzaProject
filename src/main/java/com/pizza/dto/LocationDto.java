package com.pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocationDto extends BaseEntityDto {

    private String name;
    private String address;
    private PizzeriaDto pizzeriaDto;
    private List<IngredientStockDto> ingredientStocksDto;
}
