package com.pizza.service;

import com.pizza.entity.Employee;
import com.pizza.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            throw new NoSuchElementException("No such employee.");
        }
    }

    public List<Employee> findEmployeeByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    public List<Employee> findEmployeeByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }

    public List<Employee> findEmployeesByDateOfBirth(LocalDate dateOfBirth) {
        return employeeRepository.findByDateOfBirth(dateOfBirth);
    }

    public List<Employee> findEmployeesByPosition(String position) {
        return employeeRepository.findByPosition(position);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
