package com.pizza;

import com.pizza.restcontroller.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PizzaProjectApplicationTests {

    @Autowired
    private ClientRestController clientRestController;

    @Autowired
    private EmployeeRestController employeeRestController;

    @Autowired
    private IngredientStockRestController ingredientStockRestController;

    @Autowired
    private LocationRestController locationRestController;

    @Autowired
    private MenuRestController menuRestController;

    @Autowired
    private OwnerRestController ownerRestController;

    @Autowired
    private PizzaRestController pizzaRestController;

    @Autowired
    private PizzeriaRestController pizzeriaRestController;

    @Autowired
    private ProductOrderRestController productOrderRestController;

    @Autowired
    private ReservationRestController reservationRestController;

    @Test
    void contextLoads() {
        assertThat(clientRestController).isNotNull();
        assertThat(employeeRestController).isNotNull();
        assertThat(ingredientStockRestController).isNotNull();
        assertThat(locationRestController).isNotNull();
        assertThat(menuRestController).isNotNull();
        assertThat(ownerRestController).isNotNull();
        assertThat(pizzaRestController).isNotNull();
        assertThat(pizzeriaRestController).isNotNull();
        assertThat(productOrderRestController).isNotNull();
        assertThat(reservationRestController).isNotNull();
    }

}
