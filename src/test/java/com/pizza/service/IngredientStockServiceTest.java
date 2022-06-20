package com.pizza.service;

import com.pizza.entity.IngredientStock;
import com.pizza.entity.Location;
import com.pizza.repository.IngredientStockRepository;
import liquibase.pro.packaged.P;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IngredientStockServiceTest {

    @InjectMocks
    private IngredientStockService ingredientStockService;

    @Mock
    private IngredientStockRepository ingredientStockRepository;

    @Mock
    private LocationService locationService;

    private IngredientStock ingredientStock1;
    private IngredientStock ingredientStock2;
    private Location location1;

    @BeforeEach
    public void init() {
        ingredientStockRepository = mock(IngredientStockRepository.class);
        locationService = mock(LocationService.class);
        ingredientStockService = new IngredientStockService(ingredientStockRepository, locationService);

        initializeLocations();

        initializeIngredientStocks();
    }

    private void initializeIngredientStocks() {
        ingredientStock1 = new IngredientStock("salt", 200L, LocalDate.of(2030, 1, 1), location1);
        ingredientStock1.setId(1L);

        ingredientStock2 = new IngredientStock("sugar", 300L, LocalDate.of(2030, 1, 1), location1);
        ingredientStock1.setId(2L);
    }

    private void initializeLocations() {
        location1 = new Location();
        location1.setId(1L);
        location1.setName("Location1");
        location1.setAddress("Ploiesti");
    }

    @Test
    public void saveLocationTest() {
        when(locationService.findLocationById(eq(location1.getId()))).thenReturn(location1);
        when(ingredientStockRepository.save(eq(ingredientStock1))).thenReturn(ingredientStock1);

        IngredientStock savedIngredientStock = ingredientStockService.saveIngredientStock(ingredientStock1, location1.getId());

        assertEquals(ingredientStock1.getId(), savedIngredientStock.getId());

        verify(ingredientStockRepository, times(1)).save(ingredientStock1);
    }

    @Test
    public void findAllIngredientStocksTest() {
        List<IngredientStock> ingredientStocks = Arrays.asList(ingredientStock1, ingredientStock2);

        when(ingredientStockRepository.findAll()).thenReturn(ingredientStocks);

        assertEquals(ingredientStocks.size(), ingredientStockService.findAllIngredientStocks().size());

        verify(ingredientStockRepository, times(1)).findAll();
    }

    @Test
    public void findIngredientStockByIdTest() {
        when(ingredientStockRepository.findById(eq(ingredientStock1.getId()))).thenReturn(Optional.of(ingredientStock1));

        IngredientStock ingredientStockById = ingredientStockService.findIngredientStockById(ingredientStock1.getId());

        assertEquals(ingredientStock1.getId(), ingredientStockById.getId());

        verify(ingredientStockRepository).findById(ingredientStockById.getId());
    }

    @Test
    public void findIngredientStockByNameTest() {
        when(ingredientStockRepository.findByName(eq(ingredientStock1.getName()))).thenReturn(Optional.of(ingredientStock1));

        IngredientStock ingredientStockByName = ingredientStockService.findIngredientStockByName(ingredientStock1.getName());

        assertEquals(ingredientStock1.getId(), ingredientStockByName.getId());

        verify(ingredientStockRepository).findByName(ingredientStockByName.getName());
    }

    @Test
    public void findIngredientStocksByQuantityTest() {
        List<IngredientStock> ingredientStocks = Arrays.asList(ingredientStock1);

        when(ingredientStockRepository.findByQuantity(ingredientStock1.getQuantity())).thenReturn(ingredientStocks);

        assertEquals(ingredientStocks.size(), ingredientStockService.findIngredientStocksByQuantity(ingredientStock1.getQuantity()).size());

        verify(ingredientStockRepository).findByQuantity(ingredientStock1.getQuantity());
    }

    @Test
    public void findIngredientStocksByExpirationDateTest() {
        List<IngredientStock> ingredientStocks = Arrays.asList(ingredientStock1, ingredientStock2);

        LocalDate expirationDate = LocalDate.of(2030, 1, 1);
        when(ingredientStockRepository.findByExpirationDate(eq(expirationDate))).thenReturn(ingredientStocks);

        assertEquals(ingredientStocks.size(), ingredientStockService.findIngredientStocksByExpirationDate(expirationDate).size());

        verify(ingredientStockRepository).findByExpirationDate(expirationDate);
    }

    @Test
    public void updateIngredientStockTest() {
        when(ingredientStockRepository.findById(eq(ingredientStock1.getId()))).thenReturn(Optional.of(ingredientStock1));
        ingredientStock1.setQuantity(500L);
        when(locationService.findLocationById(eq(location1.getId()))).thenReturn(location1);
        when(ingredientStockRepository.save(eq(ingredientStock1))).thenReturn(ingredientStock1);

        IngredientStock updatedIngredientStock = ingredientStockService.updateIngredientStock(ingredientStock1.getId(), ingredientStock1, location1.getId());

        assertEquals(ingredientStock1.getId(), updatedIngredientStock.getId());
    }

    @Test
    public void deleteIngredientStockByIdTest() {
        when(ingredientStockRepository.findById(ingredientStock1.getId())).thenReturn(Optional.of(ingredientStock1));
        doNothing().when(ingredientStockRepository).deleteById(ingredientStock1.getId());

        ingredientStockService.deleteIngredientStockById(ingredientStock1.getId());

        verify(ingredientStockRepository, times(1)).deleteById(ingredientStock1.getId());
    }
}
