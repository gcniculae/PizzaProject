package com.pizza.dto;

import com.pizza.entity.Pizzeria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OwnerDto extends PersonDto {

    private Pizzeria pizzeria;
    private List<EmployeeDto> employeesDto;

    public OwnerDto(String firstName, String lastName, LocalDate dateOfBirth, Pizzeria pizzeria, List<EmployeeDto> employeesDto) {
        super(firstName, lastName, dateOfBirth);
        this.pizzeria = pizzeria;
        this.employeesDto = employeesDto;
    }
}
