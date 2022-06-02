package com.pizza.entity;

import lombok.Builder;

import java.util.List;

@Builder
public class PizzeriaBuilder {

    private Owner owner;
    private List<Menu> menus;
    private List<Location> locations;
    private List<IngredientStock> ingredientStocks;

//    public Pizzeria build() {
//        Pizzeria pizzeria = new Pizzeria();
//        pizzeria.setOwner(owner);
//        pizzeria.setMenu(menus);
//        pizzeria.setLocations(locations);
//        pizzeria.setReservations(reservations);
//
//        return pizzeria;
//    }
//
//    private List<Reservation> reservations;
//
//    public PizzeriaBuilder setOwner(Owner owner) {
//        this.owner = owner;
//        return this;
//    }
//
//    public PizzeriaBuilder setMenu(List<Menu> menus) {
//        this.menus = menus;
//        return this;
//    }
//
//    public PizzeriaBuilder setIngredientStock(List<IngredientStock> ingredientStocks) {
//        this.ingredientStocks = ingredientStocks;
//        return this;
//    }
//
//    public PizzeriaBuilder setReservations(List<Reservation> reservations) {
//        this.reservations = reservations;
//        return this;
//    }
}
