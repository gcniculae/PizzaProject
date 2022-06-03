package com.pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StaffDto extends BaseEntityDto {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
