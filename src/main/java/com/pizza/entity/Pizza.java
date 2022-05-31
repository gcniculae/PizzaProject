package com.pizza.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Pizza extends BaseEntity {

    private String name;

    @ManyToOne
    private ProductOrder order;

    private double price;

    public Pizza(String name, ProductOrder order, double price) {
        this.name = name;
        this.order = order;
        this.price = price;
    }

    public Pizza() {
    }
}
