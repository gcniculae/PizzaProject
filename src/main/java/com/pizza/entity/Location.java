package com.pizza.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Location extends BaseEntity {

    @ManyToOne
    private Pizzeria pizzeria;

    public Location() {
    }
}
