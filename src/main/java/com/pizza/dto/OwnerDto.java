package com.pizza.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OwnerDto extends PersonDto {

    private Long pizzeriaId;
//    private List<EmployeeDto> employeesDto;

    public OwnerDto(OwnerDtoBuilder ownerDtoBuilder) {
        this.pizzeriaId = ownerDtoBuilder.pizzeriaId;
//        this.employeesDto = ownerDtoBuilder.employeesDto;
    }

    public static class OwnerDtoBuilder extends PersonDtoBuilder<OwnerDtoBuilder> {

        private Long pizzeriaId;
//        private List<EmployeeDto> employeesDto;

        public OwnerDtoBuilder setPizzeriaDto(Long pizzeriaId) {
            this.pizzeriaId = pizzeriaId;
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
