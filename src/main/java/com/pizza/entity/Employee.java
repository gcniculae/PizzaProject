package com.pizza.entity;

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
public class Employee extends Person {

    private Position position;

    @ManyToOne
    private Owner owner;

    public Employee(String firstName, String lastName, String phoneNumber, LocalDate datOfBirth, String address, Position position) {
        super(firstName, lastName, phoneNumber, datOfBirth, address);
        this.position = position;
    }
}
