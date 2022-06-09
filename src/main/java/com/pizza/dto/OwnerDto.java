package com.pizza.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OwnerDto extends PersonDto {

    public OwnerDto(OwnerDtoBuilder ownerDtoBuilder) {
        super(ownerDtoBuilder);
    }

    public static class OwnerDtoBuilder extends PersonDtoBuilder<OwnerDtoBuilder> {

        @Override
        public OwnerDtoBuilder getThisDto() {
            return this;
        }

        @Override
        public OwnerDto buildDto() {
            return new OwnerDto(this);
        }
    }
}
