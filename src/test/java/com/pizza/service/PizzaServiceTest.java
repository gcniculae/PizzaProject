package com.pizza.service;

import com.pizza.entity.Pizza;
import com.pizza.repository.PizzaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

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
        pizza1.setId(1L);

        pizza2 = new Pizza();
        pizza1.setName("Cheese");
        pizza1.setId(2L);
    }

    
}
