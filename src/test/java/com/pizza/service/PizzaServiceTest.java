package com.pizza.service;

import com.pizza.entity.Pizza;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.PizzaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PizzaServiceTest {

    @InjectMocks
    private PizzaService pizzaService;

    @Mock
    private PizzaRepository pizzaRepository;

    private Pizza pizza1;
    private Pizza pizza2;

    @BeforeEach
    public void init() {
        pizzaRepository = mock(PizzaRepository.class);
        pizzaService = new PizzaService(pizzaRepository);

        initializePizzas();
    }

    private void initializePizzas() {
        pizza1 = new Pizza();
        pizza1.setName("Chicken");
        pizza1.setPrice(25.0);
        pizza1.setId(1L);

        pizza2 = new Pizza();
        pizza1.setName("Cheese");
        pizza2.setPrice(25.0);
        pizza1.setId(2L);
    }

    @Test
    public void savePizzaTest() {
        when(pizzaRepository.save(eq(pizza1))).thenReturn(pizza1);

        Pizza savedPizza = pizzaService.savePizza(pizza1);

        assertEquals(pizza1.getId(), savedPizza.getId());

        verify(pizzaRepository).save(pizza1);
    }

    @Test
    public void findExistentPizzaByIdTest() {
        when(pizzaRepository.findById(eq(pizza1.getId()))).thenReturn(Optional.of(pizza1));

        Pizza pizzaById = pizzaService.findPizzaById(pizza1.getId());

        assertEquals(pizza1.getId(), pizzaById.getId());

        verify(pizzaRepository, times(1)).findById(pizza1.getId());
    }

    @Test
    public void findAllPizzaTest() {
        List<Pizza> pizzas = Arrays.asList(pizza1, pizza2);

        when(pizzaRepository.findAll()).thenReturn(pizzas);

        assertEquals(pizzas.size(), pizzaService.findAllPizza().size());

        verify(pizzaRepository, times(1)).findAll();
    }

    @Test
    public void findNonexistentPizzaByIdTest() {
        when(pizzaRepository.findById(eq(pizza1.getId()))).thenThrow(new NotFoundException("No such pizza found.", "pizza.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> pizzaRepository.findById(pizza1.getId()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such pizza found.");
    }

    @Test
    public void findPizzaByNameTest() {
        when(pizzaRepository.findByName(eq(pizza1.getName()))).thenReturn(Optional.of(pizza1));

        Pizza pizzaByName = pizzaService.findPizzaByName(pizza1.getName());

        assertEquals(pizza1.getId(), pizzaByName.getId());

        verify(pizzaRepository, times(1)).findByName(pizza1.getName());
    }

    @Test
    public void findPizzasByPriceTest() {
        List<Pizza> pizzas = Arrays.asList(pizza1, pizza2);

        Double price = 25.0;
        when(pizzaRepository.findByPrice(eq(price))).thenReturn(pizzas);

        assertEquals(pizzas.size(), pizzaService.findPizzasByPrice(price).size());
    }

    @Test
    public void updatePizzaTest() {
        when(pizzaRepository.findById(eq(pizza1.getId()))).thenReturn(Optional.of(pizza1));
        pizza1.setPrice(30.0);
        when(pizzaRepository.save(eq(pizza1))).thenReturn(pizza1);

        Pizza updatedPizza = pizzaService.updatePizza(pizza1.getId(), pizza1);

        assertEquals(pizza1.getId(), updatedPizza.getId());
    }

    @Test
    public void deleteExistentPizzaById() {
        when(pizzaRepository.findById(eq(pizza1.getId()))).thenReturn(Optional.of(pizza1));
        doNothing().when(pizzaRepository).deleteById(pizza1.getId());

        pizzaService.deletePizzaById(pizza1.getId());

        verify(pizzaRepository, times(1)).deleteById(pizza1.getId());
    }

    @Test
    public void deleteNonexistentPizzaById() {
        when(pizzaRepository.findById(eq(pizza1.getId()))).thenThrow(new NotFoundException("No such pizza found.", "pizza.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> pizzaRepository.findById(pizza1.getId()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such pizza found.");
    }

    @Test
    public void deleteExistentPizzaByName() {
        when(pizzaRepository.findByName(eq(pizza1.getName()))).thenReturn(Optional.of(pizza1));
        doNothing().when(pizzaRepository).deleteByName(pizza1.getName());

        pizzaService.deletePizzaByName(pizza1.getName());

        verify(pizzaRepository, times(1)).deleteByName(pizza1.getName());
    }

    @Test
    public void deleteNonExistentPizzaByName() {
        when(pizzaRepository.findByName(eq(pizza1.getName()))).thenThrow(new NotFoundException("No such pizza found.", "pizza.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> pizzaRepository.findByName(pizza1.getName()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such pizza found.");
    }
}
