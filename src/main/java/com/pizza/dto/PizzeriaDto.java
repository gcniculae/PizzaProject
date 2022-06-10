package com.pizza.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public class PizzeriaDto extends BaseEntityDto {

    @NotEmpty
    @Column(unique = true)
    private String name;

    @NotNull
    private Long ownerId;

    @Builder
    public static class PizzeriaDtoBuilder {

        private String name;
        private Long ownerId;
    }
}
