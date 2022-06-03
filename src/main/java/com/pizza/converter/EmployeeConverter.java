package com.pizza.converter;

import com.pizza.dto.EmployeeDto;
import com.pizza.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter extends BaseConverter<EmployeeDto, Employee> {

    @Override
    public EmployeeDto transformFromEntityToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee, employeeDto);

        return employeeDto;
    }

    @Override
    public Employee transformFromDtoToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);

        return employee;
    }
}
