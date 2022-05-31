package com.pizza.entity;

import lombok.Getter;

@Getter
public enum Position {

    COOK(3000),
    CASHIER(2500),
    WAITER(2700),
    DELIVERY(3000);

    private double salary;

    Position(double salary) {
        this.salary = salary;
    }
}
