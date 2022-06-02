package com.pizza.restcontroller;

import com.pizza.service.IngredientStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientStockRestController {

    private final IngredientStockService ingredientStockService;

    @Autowired
    public IngredientStockRestController(IngredientStockService ingredientStockService) {
        this.ingredientStockService = ingredientStockService;
    }
}
