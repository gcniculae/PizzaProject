package com.pizza.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pizza extends BaseEntity {

    private String name;

    private Double price;

    @ManyToMany
    private List<ProductOrder> orders;
}
