package com.pizza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Location extends BaseEntity {

    private String name;

    @ManyToOne
    private Pizzeria pizzeria;

    @OneToMany(mappedBy = "location")
    private List<Reservation> reservations;

    public Location(String name, Pizzeria pizzeria) {
        this.name = name;
        this.pizzeria = pizzeria;
    }
}
