package com.pizza.transformer;

import com.pizza.dto.EmployeeDto;
import com.pizza.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeTransformer {

    public EmployeeDto transformFromEmployeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee, employeeDto);

        return employeeDto;
    }

    public Employee transformFromEmployeeDtoToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);

        return employee;
    }
}
