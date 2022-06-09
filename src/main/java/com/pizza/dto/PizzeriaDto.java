package com.pizza.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class PizzeriaDto extends BaseEntityDto {

    private String name;
    private Long ownerId;
//    private List<EmployeeDto> employeesDto;
//    private List<LocationDto> locationsDto;
//    private List<MenuDto> menuDto;

    @Builder
    public static class PizzeriaDtoBuilder {

        private String name;
        private Long ownerId;
//        private List<MenuDto> menuDto;
//        private List<LocationDto> locationsDto;
//        private List<MenuDto> menuDto;
    }
}
