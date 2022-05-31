package com.pizza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Location extends BaseEntity {

    private String name;

    @ManyToOne
    private Pizzeria pizzeria;

    public Location(String name, Pizzeria pizzeria) {
        this.name = name;
        this.pizzeria = pizzeria;
    }
}
