package com.pizza.service;

import com.pizza.repository.IngredientStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientStockService {

    private final IngredientStockRepository ingredientStockRepository;

    @Autowired
    public IngredientStockService(IngredientStockRepository ingredientStockRepository) {
        this.ingredientStockRepository = ingredientStockRepository;
    }
}
