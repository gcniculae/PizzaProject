package com.pizza.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PizzeriaDto extends BaseEntityDto {

    private OwnerDto ownerDto;
    private List<MenuDto> menuDto;
    private List<LocationDto> locationsDto;
    private List<IngredientStockDto> ingredientStockListDto;
    private List<ReservationDto> reservationsDto;

    @Builder
    public static class PizzeriaDtoBuilder {

        private OwnerDto ownerDto;
        private List<MenuDto> menuDto;
        private List<LocationDto> locationsDto;
        private List<IngredientStockDto> ingredientStockListDto;
        private List<ReservationDto> reservationsDto;
    }
}
