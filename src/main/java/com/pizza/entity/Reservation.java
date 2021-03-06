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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation extends BaseEntity {

    @NotEmpty
    @Column(unique = true)
    private String name;

    @ManyToOne
    @NotNull
    private Client client;

    @ManyToOne
    @NotNull
    private Location location;
}
