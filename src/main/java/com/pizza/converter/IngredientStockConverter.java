package com.pizza.converter;

import com.pizza.dto.IngredientStockDto;
import com.pizza.entity.IngredientStock;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class IngredientStockConverter extends BaseConverter<IngredientStockDto, IngredientStock> {

    @Override
    public IngredientStockDto convertFromEntityToDto(IngredientStock ingredientStock) {
        IngredientStockDto ingredientStockDto = new IngredientStockDto();
        BeanUtils.copyProperties(ingredientStock, ingredientStockDto);

        return ingredientStockDto;
    }

    @Override
    public IngredientStock convertFromDtoToEntity(IngredientStockDto ingredientStockDto) {
        IngredientStock ingredientStock = new IngredientStock();
        BeanUtils.copyProperties(ingredientStockDto, ingredientStock);

        return ingredientStock;
    }
}
