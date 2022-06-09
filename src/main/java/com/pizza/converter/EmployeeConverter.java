package com.pizza.converter;

import com.pizza.dto.EmployeeDto;
import com.pizza.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter extends BaseConverter<EmployeeDto, Employee> {

    @Override
    public EmployeeDto convertFromEntityToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee, employeeDto, "owner");
        employeeDto.setOwnerId(employee.getOwner().getId());

        return employeeDto;
    }

    @Override
    public Employee convertFromDtoToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee, "ownerDtoId");

        return employee;
    }
}
