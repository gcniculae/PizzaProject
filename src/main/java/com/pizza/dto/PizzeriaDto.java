package com.pizza.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class PizzeriaDto extends BaseEntityDto {

    private String name;
    private Long ownerId;

    @Builder
    public static class PizzeriaDtoBuilder {

        private String name;
        private Long ownerId;
    }
}
