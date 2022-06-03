package com.pizza.restcontroller;

import com.pizza.converter.PizzaConverter;
import com.pizza.service.PizzeriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/pizzeria")
@CrossOrigin(origins = "*")
public class PizzeriaRestController {

    private final PizzeriaService pizzeriaService;
    private PizzaConverter pizzaConverter;

    @Autowired
    public PizzeriaRestController(PizzeriaService pizzeriaService, PizzaConverter pizzaConverter) {
        this.pizzeriaService = pizzeriaService;
        this.pizzaConverter = pizzaConverter;
    }
}
