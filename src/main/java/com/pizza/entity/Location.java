package com.pizza.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location extends BaseEntity {

    private String name;
    private String address;

    @ManyToOne
    private Pizzeria pizzeria;

    @OneToMany(mappedBy = "location")
    private List<Reservation> reservations;
}
