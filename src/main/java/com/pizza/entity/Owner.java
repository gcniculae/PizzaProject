package com.pizza.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

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
