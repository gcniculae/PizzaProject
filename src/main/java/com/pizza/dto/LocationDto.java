package com.pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocationDto extends BaseEntityDto {

    @NotEmpty
    @Column(unique = true)
    private String name;

    @NotNull
    private String address;

    @NotNull
    private Long pizzeriaId;
}
