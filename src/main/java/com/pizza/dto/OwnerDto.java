package com.pizza.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OwnerDto extends PersonDto {

    private PizzeriaDto pizzeriaDto;
//    private List<EmployeeDto> employeesDto;

    public OwnerDto(OwnerDtoBuilder ownerDtoBuilder) {
        this.pizzeriaDto = ownerDtoBuilder.pizzeriaDto;
//        this.employeesDto = ownerDtoBuilder.employeesDto;
    }

    public static class OwnerDtoBuilder extends PersonDtoBuilder<OwnerDtoBuilder> {

        private PizzeriaDto pizzeriaDto;
//        private List<EmployeeDto> employeesDto;

        public OwnerDtoBuilder setPizzeriaDto(PizzeriaDto pizzeriaDto) {
            this.pizzeriaDto = pizzeriaDto;
            return this;
        }

//        public OwnerDtoBuilder setEmployeesDtoList(List<EmployeeDto> employeesDto) {
//            this.employeesDto = employeesDto;
//            return this;
//        }

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
