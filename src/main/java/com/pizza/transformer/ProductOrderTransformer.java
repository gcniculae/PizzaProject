package com.pizza.transformer;

import com.pizza.dto.ProductOrderDto;
import com.pizza.entity.ProductOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductOrderTransformer {

    public ProductOrderDto transformFromProductOrderToProductOrderDto(ProductOrder productOrder) {
        ProductOrderDto productOrderDto = new ProductOrderDto();
        BeanUtils.copyProperties(productOrder, productOrderDto);

        return productOrderDto;
    }

    public ProductOrder transformFromProductOrderDtoToProductOrder(ProductOrderDto productOrderDto) {
        ProductOrder productOrder = new ProductOrder();
        BeanUtils.copyProperties(productOrder, productOrderDto);

        return productOrder;
    }
}
