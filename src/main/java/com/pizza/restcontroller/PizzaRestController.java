package com.pizza.restcontroller;

import com.pizza.converter.PizzaConverter;
import com.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/pizzas")
@CrossOrigin(origins = "*")
public class PizzaRestController {

    private final PizzaService pizzaService;
    private final PizzaConverter pizzaConverter;

    @Autowired
    public PizzaRestController(PizzaService pizzaService, PizzaConverter pizzaConverter) {
        this.pizzaService = pizzaService;
        this.pizzaConverter = pizzaConverter;
    }

    
}
