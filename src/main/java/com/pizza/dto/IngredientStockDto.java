package com.pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IngredientStockDto extends BaseEntityDto {

    @NotEmpty
    @Column(unique = true)
    private String name;

    @NotNull
    private Long quantity;

    @NotNull
    private LocalDate expirationDate;

    @NotNull
    private Long locationId;
}
