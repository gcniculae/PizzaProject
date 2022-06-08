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
public class Owner extends Person {

    @OneToOne
    @NotNull
    private Pizzeria pizzeria;

    @OneToMany(mappedBy = "owner")
    private List<Employee> employees;

//    public Owner(String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth, String address, Pizzeria pizzeria) {
//        super(firstName, lastName, phoneNumber, dateOfBirth, address);
//        this.pizzeria = pizzeria;
//    }

    public Owner(OwnerBuilder ownerBuilder) {
        super(ownerBuilder);
        this.pizzeria = ownerBuilder.pizzeria;
        this.employees = ownerBuilder.employees;
    }

//    public Owner() {
//    }

    public static class OwnerBuilder extends PersonBuilder<OwnerBuilder> {

        private Pizzeria pizzeria;
        private List<Employee> employees;

        public OwnerBuilder() {
        }

        public OwnerBuilder setPizzeria(Pizzeria pizzeria) {
            this.pizzeria = pizzeria;
            return this;
        }

        public OwnerBuilder setEmployeeList(List<Employee> employees) {
            this.employees = employees;
            return this;
        }

        @Override
        public OwnerBuilder getThis() {
            return null;
        }

        @Override
        public Owner build() {
            return new Owner(this);
        }
    }
}
