package com.pizza.dto;

import com.pizza.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IngredientStockDto extends BaseEntityDto {

    private String name;
    private Long quantity;
    private LocalDate expirationDate;
    private Location location;
}
