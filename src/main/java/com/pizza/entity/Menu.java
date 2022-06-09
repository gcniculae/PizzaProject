package com.pizza.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Menu extends BaseEntity {

    @NotNull
    @NotEmpty
    private String name;

    @ManyToOne
    private Pizzeria pizzeria;
}
