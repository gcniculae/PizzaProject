package com.pizza.restcontroller;

import com.pizza.converter.ProductOrderConverter;
import com.pizza.dto.ProductOrderDto;
import com.pizza.entity.ProductOrder;
import com.pizza.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping(path = "/all")
    public ResponseEntity<List<ProductOrderDto>> findAllProductOrders(@RequestParam(name = "allProductOrders", required = false) Boolean allProductOrders,
                                                                      @RequestParam(name = "clientId", required = false) Long clientId) {

        List<ProductOrder> allProductOrdersList = new ArrayList<>();

        if (allProductOrders != null) {
            allProductOrdersList = productOrderService.findAllProductOrders();
        }
        else if (clientId != null) {
            allProductOrdersList = productOrderService.findProductOrdersByClientId(clientId);
        }

        return ResponseEntity.ok(productOrderConverter.convertFromEntityListToDtoList(allProductOrdersList));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductOrderDto> findProductOrder(@PathVariable Long id) {
        return ResponseEntity.ok(productOrderConverter.convertFromEntityToDto(productOrderService.findProductOrderById(id)));
    }

    @PostMapping
    public ResponseEntity<ProductOrderDto> addProductOrder(@RequestBody ProductOrderDto productOrderDto) {
        ProductOrder productOrder = productOrderConverter.convertFromDtoToEntity(productOrderDto);
        ProductOrder savedProductOwner = productOrderService.saveProductOrder(productOrder);

        return ResponseEntity.ok(productOrderConverter.convertFromEntityToDto(savedProductOwner));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductOrderDto> updateProductOrder(@PathVariable(name = "id") Long id, @RequestBody ProductOrderDto productOrderDto) {
        ProductOrder productOrder = productOrderConverter.convertFromDtoToEntity(productOrderDto);
        ProductOrder updatedProductOwner = productOrderService.updateProductOrder(id, productOrder);

        return ResponseEntity.ok(productOrderConverter.convertFromEntityToDto(updatedProductOwner));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProductOrderDto> deleteProductOrderById(@PathVariable Long id) {
        productOrderService.deleteProductOrderServiceById(id);

        return ResponseEntity.noContent().build();
    }
}
