package com.pizza.service;

import com.pizza.entity.Employee;
import com.pizza.entity.Position;
import com.pizza.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @PostConstruct
    public void createEmployees() {
        List<Employee> initialEmployees = new ArrayList<>();
        initialEmployees.add(new Employee("Andrei", "Ionescu", "0723001001", LocalDate.of(1970, 3, 24), "Ploiesti", Position.COOK));
        initialEmployees.add(new Employee("Ion", "Popescu", "0723001002", LocalDate.of(1985, 7, 9), "Ploiesti", Position.CASHIER));
        initialEmployees.add(new Employee("Alexandru", "Antonescu", "0723001003", LocalDate.of(1991, 10, 15), "Ploiesti", Position.DELIVERY));
        initialEmployees.add(new Employee("Ion", "Constantinescu", "0723001004", LocalDate.of(1979, 1, 30), "Ploiesti", Position.WAITER));
        initialEmployees.add(new Employee("Alin", "Ionescu", "0723001005", LocalDate.of(1984, 12, 5), "Ploiesti", Position.COOK));

        employeeRepository.saveAll(initialEmployees);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
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

    public List<Employee> findEmployeesByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    public List<Employee> findEmployeesByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }

    public List<Employee> findEmployeesByDateOfBirth(LocalDate dateOfBirth) {
        return employeeRepository.findByDateOfBirth(dateOfBirth);
    }

    public List<Employee> findEmployeesByPosition(Position position) {
        return employeeRepository.findByPosition(position);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee employeeById = findEmployeeById(id);
        employee.setId(employeeById.getId());

        return saveEmployee(employee);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
