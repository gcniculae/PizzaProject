package com.pizza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Pizzeria extends BaseEntity {

    @OneToOne
    private Owner owner;

    @OneToMany(mappedBy = "pizzeria")
    private List<Menu> menu;

    @OneToMany(mappedBy = "pizzeria")
    private List<Location> locations;

    @OneToMany(mappedBy = "pizzeria")
    private List<IngredientStock> ingredientStockList;

    @OneToMany(mappedBy = "pizzeria")
    private List<Reservation> reservations;

    public Pizzeria(Owner owner, List<Menu> menu, List<Location> locations, List<IngredientStock> ingredientStockList, List<Reservation> reservations) {
        this.owner = owner;
        this.menu = menu;
        this.locations = locations;
        this.ingredientStockList = ingredientStockList;
        this.reservations = reservations;
    }
}
