package com.pizza.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
public enum Position {

    MANAGER(5000),
    COOK(3000),
    CASHIER(2500),
    WAITER(2700),
    DELIVERY(3000);

    private double salary;
}
