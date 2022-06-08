package com.pizza.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location extends BaseEntity {

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String name;

    @NotNull
    @NotEmpty
    private String address;

    @ManyToOne
    private Pizzeria pizzeria;

    @OneToMany(mappedBy = "location")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "location")
    private List<IngredientStock> ingredientStocks;
}
