package com.pizza.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
public class Staff extends BaseEntity {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public Staff(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Staff() {
    }
}
