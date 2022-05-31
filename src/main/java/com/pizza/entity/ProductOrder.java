package com.pizza.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ProductOrder extends BaseEntity {

    @OneToMany(mappedBy = "order")
    private List<Pizza> pizzas;

    public ProductOrder(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
