package com.pizza.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class ProductOrder extends BaseEntity {

    @OneToMany(mappedBy = "order")
    private List<Pizza> pizzas;

    public ProductOrder(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public ProductOrder() {
    }
}
