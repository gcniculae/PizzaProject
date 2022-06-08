package com.pizza.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Pizzeria extends BaseEntity {

    @NotNull
    private String name;

    @OneToOne
    @NotNull
    private Owner owner;

    @OneToMany(mappedBy = "pizzeria")
    private List<Menu> menu;

    @OneToMany(mappedBy = "pizzeria")
    private List<Location> locations;

    private Pizzeria(PizzeriaBuilder pizzeriaBuilder) {
        this.name = pizzeriaBuilder.name;
        this.owner = pizzeriaBuilder.owner;
        this.menu = pizzeriaBuilder.menus;
        this.locations = pizzeriaBuilder.locations;
    }

    //    @Builder
    public static class PizzeriaBuilder {

        private String name;
        private Owner owner;
        private List<Menu> menus;
        private List<Location> locations;

        public PizzeriaBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PizzeriaBuilder setOwner(Owner owner) {
            this.owner = owner;
            return this;
        }

        public PizzeriaBuilder setMenu(List<Menu> menus) {
            this.menus = menus;
            return this;
        }

        public PizzeriaBuilder setLocation(List<Location> locations) {
            this.locations = locations;
            return this;
        }

        public Pizzeria build() {
            return new Pizzeria(this);
        }
    }
}
