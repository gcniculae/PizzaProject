package com.pizza.restcontroller;

import com.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/pizza")
@CrossOrigin(origins = "*")
public class PizzaRestController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaRestController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }
}
