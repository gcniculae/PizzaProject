package com.pizza.transformer;

import com.pizza.dto.IngredientStockDto;
import com.pizza.entity.IngredientStock;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class IngredientStockTransformer {

    public IngredientStockDto transformFromIngredientStockToIngredientStockDto(IngredientStock ingredientStock) {
        IngredientStockDto ingredientStockDto = new IngredientStockDto();
        BeanUtils.copyProperties(ingredientStock, ingredientStockDto);

        return ingredientStockDto;
    }

    public IngredientStock transformFromIngredientStockDtoToIngredientStock(IngredientStockDto ingredientStockDto) {
        IngredientStock ingredientStock = new IngredientStock();
        BeanUtils.copyProperties(ingredientStockDto, ingredientStock);

        return ingredientStock;
    }
}
