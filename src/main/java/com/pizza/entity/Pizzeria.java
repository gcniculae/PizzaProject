package com.pizza.entity;

import lombok.Builder;
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

    @Builder
    public static class PizzeriaBuilder {

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
}
