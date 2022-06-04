package com.pizza.dto;

import com.pizza.entity.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto extends PersonDto {

    private Position position;
    private OwnerDto ownerDto;

    public EmployeeDto(String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth, String address, Position position, OwnerDto ownerDto) {
        super(firstName, lastName, phoneNumber, dateOfBirth, address);
        this.position = position;
        this.ownerDto = ownerDto;
    }
}
