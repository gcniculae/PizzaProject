package com.pizza.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location extends BaseEntity {

    @NotEmpty
    @Column(unique = true)
    private String name;

    @NotEmpty
    private String address;

    @ManyToOne
    @NotNull
    private Pizzeria pizzeria;

    @OneToMany(mappedBy = "location")
    private List<Employee> employees;

    @OneToMany(mappedBy = "location")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "location")
    private List<IngredientStock> ingredientStocks;
}
