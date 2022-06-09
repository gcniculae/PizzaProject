package com.pizza.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Employee extends Person {

    @NotNull
    @NotEmpty
    private Position position;

    @ManyToOne
    @NotNull
    @NotEmpty
    private Owner owner;

    public Employee(EmployeeBuilder employeeBuilder) {
        super(employeeBuilder);
        this.position = employeeBuilder.position;
        this.owner = employeeBuilder.owner;
    }

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
