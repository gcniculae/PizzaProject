package com.pizza.restcontroller;

import com.pizza.service.OwnerService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwnerRestController {

    private final OwnerService ownerService;

    public OwnerRestController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
}
