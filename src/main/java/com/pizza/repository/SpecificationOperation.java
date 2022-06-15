package com.pizza.repository;

public enum SpecificationOperation {

    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_OR_EQUAL_THAN(">="),
    LESS_OR_EQUAL_THAN("<="),
    EQUAL("=="),
    NOT_EQUAL("!="),
    MATCH("match"),
    IN("in"),
    NOT_IN("notIn"),
    ENDS_WITH("endsWith"),
    STARTS_WITH("startsWith");

    private String symbol;

    SpecificationOperation(String symbol) {
        this.symbol = symbol;
    }
}
