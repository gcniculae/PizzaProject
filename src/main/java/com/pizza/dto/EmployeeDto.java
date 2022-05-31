package com.pizza.dto;

import com.pizza.entity.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto extends StaffDto {

    private Position position;
    private OwnerDto ownerDto;

    public EmployeeDto(String firstName, String lastName, LocalDate datOfBirth, Position position, OwnerDto ownerDto) {
        super(firstName, lastName, datOfBirth);
        this.position = position;
        this.ownerDto = ownerDto;
    }
}
