package com.pizza.restcontroller;

import com.pizza.converter.ProductOrderConverter;
import com.pizza.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/productOrders")
@CrossOrigin(origins = "*")
public class ProductOrderRestController {

    private final ProductOrderService productOrderService;
    private final ProductOrderConverter productOrderConverter;

    @Autowired
    public ProductOrderRestController(ProductOrderService productOrderService, ProductOrderConverter productOrderConverter) {
        this.productOrderService = productOrderService;
        this.productOrderConverter = productOrderConverter;
    }
}
