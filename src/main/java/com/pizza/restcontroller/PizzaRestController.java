package com.pizza.restcontroller;

import com.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PizzaRestController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaRestController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }
}
