package com.pizza.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Employee extends Staff {

    private Position position;

    @ManyToOne
    private Owner owner;

    public Employee(String firstName, String lastName, LocalDate datOfBirth, Position position, Owner owner) {
        super(firstName, lastName, datOfBirth);
        this.position = position;
        this.owner = owner;
    }
}
