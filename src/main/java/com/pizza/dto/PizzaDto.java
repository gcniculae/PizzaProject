package com.pizza.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PizzaDto extends BaseEntityDto {

    @NotEmpty
    @Column(unique = true)
    private String name;

    @NotNull
    private Double price;
}
