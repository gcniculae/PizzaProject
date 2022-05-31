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
public class Reservation extends BaseEntity {

    @ManyToOne
    private Pizzeria pizzeria;

    public Reservation(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }
}
