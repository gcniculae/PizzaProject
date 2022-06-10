package com.pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDto extends BaseEntityDto {

    @NotEmpty
    @Column(unique = true)
    private String name;

    @NotNull
    private Long clientId;

    @NotNull
    private Long locationId;
}
