package com.pizza.converter;

import com.pizza.dto.ProductOrderDto;
import com.pizza.entity.ProductOrder;
import com.pizza.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductOrderConverter extends BaseConverter<ProductOrderDto, ProductOrder> {

    private final ProductOrderService productOrderService;

    @Autowired
    public ProductOrderConverter(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    @Override
    public ProductOrderDto convertFromEntityToDto(ProductOrder productOrder) {
        ProductOrderDto productOrderDto = new ProductOrderDto();
        productOrderDto.setId(productOrder.getId());
        productOrderDto.setClientId(productOrder.getClient().getId());

        return productOrderDto;
    }

    @Override
    public ProductOrder convertFromDtoToEntity(ProductOrderDto productOrderDto) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setClient(productOrderService.findProductOrderById(productOrderDto.getClientId()).getClient());

        return productOrder;
    }
}
