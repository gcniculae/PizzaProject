package com.pizza.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClientDto extends BaseEntityDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private static int number = 0;
    private String clientCode;

    public ClientDto(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        clientCode = "C" + number++ + firstName.charAt(0) + lastName.charAt(0);
    }
}
