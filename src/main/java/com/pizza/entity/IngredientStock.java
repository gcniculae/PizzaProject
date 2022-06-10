package com.pizza.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IngredientStock extends BaseEntity {

    @NotEmpty
    @Column(unique = true)
    private String name;

    @javax.validation.constraints.NotNull
    private Long quantity;

    @NotNull
    private LocalDate expirationDate;

    @ManyToOne
    @NotNull
    private Location location;
}
