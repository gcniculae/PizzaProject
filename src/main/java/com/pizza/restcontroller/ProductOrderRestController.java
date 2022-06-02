package com.pizza.restcontroller;

import com.pizza.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductOrderRestController {

    private final ProductOrderService productOrderService;

    @Autowired
    public ProductOrderRestController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }
}
