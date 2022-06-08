package com.pizza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Employee extends Person {

    @Column(nullable = false)
    private Position position;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Owner owner;

    public Employee(EmployeeBuilder employeeBuilder) {
        super(employeeBuilder);
        this.position = employeeBuilder.position;
        this.owner = employeeBuilder.owner;
    }

//    public Employee() {
//    }

//    public Employee(String firstName, String lastName, String phoneNumber, LocalDate datOfBirth, String address, Position position) {
//        super(firstName, lastName, phoneNumber, datOfBirth, address);
//        this.position = position;
//    }

    public static class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {

        private Position position;
        private Owner owner;

        public EmployeeBuilder setPosition(Position position) {
            this.position = position;
            return this;
        }

        public EmployeeBuilder setOwner(Owner owner) {
            this.owner = owner;
            return this;
        }

//        public EmployeeBuilder() {
//        }

        @Override
        public EmployeeBuilder getThis() {
            return this;
        }

        @Override
        public Employee build() {
            return new Employee(this);
        }
    }
}
