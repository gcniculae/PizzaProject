package com.pizza.converter;

import com.pizza.dto.ProductOrderDto;
import com.pizza.entity.ProductOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductOrderConverter extends BaseConverter<ProductOrderDto, ProductOrder> {

    @Override
    public ProductOrderDto convertFromEntityToDto(ProductOrder productOrder) {
        ProductOrderDto productOrderDto = new ProductOrderDto();
        BeanUtils.copyProperties(productOrder, productOrderDto);

        return productOrderDto;
    }

    @Override
    public ProductOrder convertFromDtoToEntity(ProductOrderDto productOrderDto) {
        ProductOrder productOrder = new ProductOrder();
        BeanUtils.copyProperties(productOrder, productOrderDto);

        return productOrder;
    }
}
