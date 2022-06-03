package com.pizza.restcontroller;

import com.pizza.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/productOrder")
@CrossOrigin(origins = "*")
public class ProductOrderRestController {

    private final ProductOrderService productOrderService;

    @Autowired
    public ProductOrderRestController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }
}
