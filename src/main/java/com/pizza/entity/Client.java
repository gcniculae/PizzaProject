package com.pizza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Client extends BaseEntity {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private static int number = 0;
    private String clientCode;

    public Client(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        clientCode = "C" + number++ + firstName.charAt(0) + lastName.charAt(0);
    }
}
