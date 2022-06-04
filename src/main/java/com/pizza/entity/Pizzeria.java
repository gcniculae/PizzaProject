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

    private String name;

    @OneToOne
    private Owner owner;

    @OneToMany(mappedBy = "pizzeria")
    private List<Menu> menu;

    @OneToMany(mappedBy = "pizzeria")
    private List<Location> locations;

    @Builder
    public static class PizzeriaBuilder {

        private String name;
        private Owner owner;
        private List<Menu> menus;
        private List<Location> locations;

//        public Pizzeria build() {
//            Pizzeria pizzeria = new Pizzeria();
//            pizzeria.setName(name);
//            pizzeria.setOwner(owner);
//            pizzeria.setMenu(menus);
//            pizzeria.setLocations(locations);
//
//            return pizzeria;
//        }
//
//        public PizzeriaBuilder setName(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public PizzeriaBuilder setOwner(Owner owner) {
//            this.owner = owner;
//            return this;
//        }
//
//        public PizzeriaBuilder setMenu(List<Menu> menus) {
//            this.menus = menus;
//            return this;
//        }
//
//        public PizzeriaBuilder setLocation(List<Location> locations) {
//            this.locations = locations;
//            return this;
//        }
    }
}
