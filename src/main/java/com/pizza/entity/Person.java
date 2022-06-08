package com.pizza.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@MappedSuperclass
//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
public class Person extends BaseEntity {

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    @NotNull
    @NotEmpty
    private LocalDate dateOfBirth;

    @NotNull
    @NotEmpty
    private String address;

    protected Person(PersonBuilder<?> personBuilder) {
        this.firstName = personBuilder.firstName;
        this.lastName = personBuilder.lastName;
        this.phoneNumber = personBuilder.phoneNumber;
        this.dateOfBirth = personBuilder.dateOfBirth;
        this.address = personBuilder.address;
    }

    public Person() {
    }

    public abstract static class PersonBuilder<T extends PersonBuilder<T>> {

        private String firstName;
        private String lastName;
        private String phoneNumber;
        private LocalDate dateOfBirth;
        private String address;

        PersonBuilder() {
        }

        public abstract T getThis();

        public T setFirstName(String firstName) {
            this.firstName = firstName;
            return this.getThis();
        }

        public T setLastName(String lastName) {
            this.lastName = lastName;
            return this.getThis();
        }

        public T setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this.getThis();
        }

        public T setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this.getThis();
        }

        public T setAddress(String address) {
            this.address = address;
            return this.getThis();
        }

        public Person build() {
            return new Person(this);
        }
    }
}
