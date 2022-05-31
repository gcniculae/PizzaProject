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

    @ManyToOne
    private Pizzeria pizzeria;

    public Location(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }
}
