package com.pizza.service;

import com.pizza.entity.Location;
import com.pizza.repository.IngredientStockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class IngredientStockServiceTest {

    @InjectMocks
    private IngredientStockService ingredientStockService;

    @Mock
    private IngredientStockRepository ingredientStockRepository;

    @Mock
    private LocationService locationService;

    @BeforeEach
    public void init() {
        ingredientStockRepository = mock(IngredientStockRepository.class);
        ingredientStockService = new IngredientStockService(ingredientStockRepository, locationService);

        initializeIngredientStocks();
    }

    private void initializeIngredientStocks() {
        Location location1 = new Location();
        location1.setId(1L);
        location1.setName("Location1");
        location1.setAddress("Ploiesti");
    }

    @Test
    public void saveLocationTest() {
        
    }
}
