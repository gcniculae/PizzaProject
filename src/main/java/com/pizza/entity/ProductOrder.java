package com.pizza.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductOrder extends BaseEntity {

    @ManyToOne
    @NotNull
    private Client client;

    @ManyToMany
    private List<Pizza> pizzas;

    private Long paymentId;
}
