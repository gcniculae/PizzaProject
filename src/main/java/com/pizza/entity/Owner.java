package com.pizza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Owner extends Staff {

    @OneToOne
    private Pizzeria pizzeria;

    @OneToMany(mappedBy = "owner")
    private List<Employee> employees;

    public Owner(String firstName, String lastName, LocalDate dateOfBirth, Pizzeria pizzeria, List<Employee> employees) {
        super(firstName, lastName, dateOfBirth);
        this.pizzeria = pizzeria;
        this.employees = employees;
    }
}
