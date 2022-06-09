package com.pizza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Owner extends Person {

    public Owner(OwnerBuilder ownerBuilder) {
        super(ownerBuilder);
    }

    public static class OwnerBuilder extends PersonBuilder<OwnerBuilder> {

        @Override
        public OwnerBuilder getThis() {
            return null;
        }

        @Override
        public Owner build() {
            return new Owner(this);
        }
    }
}
