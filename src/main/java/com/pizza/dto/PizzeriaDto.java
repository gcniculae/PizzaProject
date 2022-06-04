package com.pizza.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PizzeriaDto extends BaseEntityDto {

    private String name;
    private OwnerDto ownerDto;
    private List<MenuDto> menuDto;
    private List<LocationDto> locationsDto;

    @Builder
    public static class PizzeriaDtoBuilder {

        private String name;
        private OwnerDto ownerDto;
        private List<MenuDto> menuDto;
        private List<LocationDto> locationsDto;

    }
}
