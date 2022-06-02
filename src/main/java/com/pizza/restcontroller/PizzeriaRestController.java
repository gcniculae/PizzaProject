package com.pizza.restcontroller;

import com.pizza.service.PizzeriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/pizzeria")
@CrossOrigin(origins = "*")
public class PizzeriaRestController {

    private final PizzeriaService pizzeriaService;

    @Autowired
    public PizzeriaRestController(PizzeriaService pizzeriaService) {
        this.pizzeriaService = pizzeriaService;
    }
}
