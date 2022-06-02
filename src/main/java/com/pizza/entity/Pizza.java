package com.pizza.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pizza extends BaseEntity {

    private String name;

    @ManyToOne
    private ProductOrder order;

    private double price;
}
