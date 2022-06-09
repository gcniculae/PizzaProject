package com.pizza.dto;

import com.pizza.entity.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PizzaDto extends BaseEntityDto {

    private String name;
    private Double price;
//    private List<ProductOrder> productOrders;
}
