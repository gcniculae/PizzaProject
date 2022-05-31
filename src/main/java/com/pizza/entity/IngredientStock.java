package com.pizza.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class IngredientStock extends BaseEntity {

    public IngredientStock() {
    }
}
