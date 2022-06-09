package com.pizza.dto;

import com.pizza.entity.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto extends PersonDto {

    private Position position;
    private Long ownerId;

    public EmployeeDto(EmployeeDtoBuilder employeeDtoBuilder) {
        this.position = employeeDtoBuilder.position;
        this.ownerId = employeeDtoBuilder.ownerId;
    }

    public static class EmployeeDtoBuilder extends PersonDtoBuilder<EmployeeDtoBuilder> {

        private Position position;
        private Long ownerId;

        public EmployeeDtoBuilder setPosition(Position position) {
            this.position = position;
            return this;
        }

        public EmployeeDtoBuilder setOwnerDto(Long ownerDtoId) {
            this.ownerId = ownerDtoId;
            return this;
        }

        @Override
        public EmployeeDtoBuilder getThisDto() {
            return this;
        }

        @Override
        public EmployeeDto buildDto() {
            return new EmployeeDto(this);
        }
    }
}
