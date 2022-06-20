package com.pizza.service;

import com.pizza.repository.IngredientStockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class IngredientStockServiceTest {

    @InjectMocks
    private IngredientStockService ingredientStockService;

    @Mock
    private IngredientStockRepository ingredientStockRepository;

    @BeforeEach
    public void init() {
        initializeIngredientStocks();
    }

    private void initializeIngredientStocks() {
        
    }
}
