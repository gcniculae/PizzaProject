package com.pizza.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public PizzeriaDto(OwnerDto ownerDto, List<MenuDto> menuDto, List<LocationDto> locationsDto, List<IngredientStockDto> ingredientStockListDto, List<ReservationDto> reservationsDto) {
        this.ownerDto = ownerDto;
        this.menuDto = menuDto;
        this.locationsDto = locationsDto;
        this.ingredientStockListDto = ingredientStockListDto;
        this.reservationsDto = reservationsDto;
    }
}
