package com.pizza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Employee extends Person {

    @NotNull
    private Position position;

    @ManyToOne
    @NotNull
    private Location location;

    public Employee(EmployeeBuilder employeeBuilder) {
        super(employeeBuilder);
        this.position = employeeBuilder.position;
        this.location = employeeBuilder.location;
    }

    public static class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {

        private Position position;
        private Location location;

        public EmployeeBuilder setPosition(Position position) {
            this.position = position;
            return this;
        }

        public EmployeeBuilder setLocation(Location location) {
            this.location = location;
            return this;
        }

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
