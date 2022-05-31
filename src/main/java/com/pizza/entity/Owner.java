package com.pizza.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Owner extends Staff {

    @OneToMany(mappedBy = "owner")
    private List<Employee> employees;

    public Owner(String firstName, String lastName, LocalDate datOfBirth, List<Employee> employees) {
        super(firstName, lastName, datOfBirth);
        this.employees = employees;
    }

    public Owner() {
    }
}
