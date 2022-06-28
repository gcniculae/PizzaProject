package com.pizza.restcontroller;

import com.pizza.converter.ProductOrderConverter;
import com.pizza.dto.ProductOrderDto;
import com.pizza.entity.Pizza;
import com.pizza.entity.ProductOrder;
import com.pizza.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping
    public ResponseEntity<List<ProductOrderDto>> findAllProductOrders(@RequestParam(name = "all", required = false, defaultValue = "false") Boolean all,
                                                                      @RequestParam(name = "clientId", required = false) Long clientId) {

        List<ProductOrder> allProductOrdersList = new ArrayList<>();

        if (all) {
            allProductOrdersList = productOrderService.findAllProductOrders();
        } else if (clientId != null) {
            allProductOrdersList = productOrderService.findProductOrdersByClientId(clientId);
        }

        List<ProductOrderDto> productOrdersDto = productOrderConverter.convertFromEntityListToDtoList(allProductOrdersList);
        productOrderService.addPizzasIdsToDtoList(allProductOrdersList, productOrdersDto);
        productOrderService.addClientIdToDtoList(allProductOrdersList, productOrdersDto);

        return ResponseEntity.ok(productOrdersDto);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductOrderDto> findProductOrderById(@PathVariable Long id) {
        ProductOrder productOrderById = productOrderService.findProductOrderById(id);
        ProductOrderDto productOrderDto = productOrderConverter.convertFromEntityToDto(productOrderById);
        productOrderService.addPizzasIdsToDto(productOrderDto, productOrderById);
        productOrderService.addClientId(productOrderDto, productOrderById.getClient());

        return ResponseEntity.ok(productOrderDto);
    }

    @PostMapping
    public ResponseEntity<ProductOrderDto> addProductOrder(@Valid @RequestBody ProductOrderDto productOrderDto) {
        ProductOrder productOrder = productOrderConverter.convertFromDtoToEntity(productOrderDto);
        ProductOrder savedProductOrder = productOrderService.saveProductOrder(productOrder, productOrderDto);
        ProductOrderDto savedProductOrderDto = productOrderConverter.convertFromEntityToDto(savedProductOrder);
        productOrderService.addPizzasIdsToDto(savedProductOrderDto, savedProductOrder);
        productOrderService.addClientId(savedProductOrderDto, savedProductOrder.getClient());

        return ResponseEntity.ok(savedProductOrderDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductOrderDto> updateProductOrder(@PathVariable(name = "id") Long id, @Valid @RequestBody ProductOrderDto productOrderDto) {
        ProductOrder productOrder = productOrderConverter.convertFromDtoToEntity(productOrderDto);
        ProductOrder updatedProductOwner = productOrderService.updateProductOrder(id, productOrder, productOrderDto);
        ProductOrderDto updatedProductOrderDto = productOrderConverter.convertFromEntityToDto(updatedProductOwner);
        productOrderService.addPizzasIdsToDto(updatedProductOrderDto, updatedProductOwner);
        productOrderService.addClientId(updatedProductOrderDto, updatedProductOwner.getClient());

        return ResponseEntity.ok(updatedProductOrderDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProductOrderDto> deleteProductOrderById(@PathVariable Long id) {
        productOrderService.deleteProductOrderServiceById(id);

        return ResponseEntity.noContent().build();
    }
}
