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
        initialEmployees.add(new Employee("Andrei", "Ionescu", LocalDate.of(1970, 3, 24), Position.COOK));
        initialEmployees.add(new Employee("Ion", "Popescu", LocalDate.of(1985, 7, 9), Position.CASHIER));
        initialEmployees.add(new Employee("Alexandru", "Antonescu", LocalDate.of(1991, 10, 15), Position.DELIVERY));
        initialEmployees.add(new Employee("Ion", "Constantinescu", LocalDate.of(1979, 1, 30), Position.WAITER));
        initialEmployees.add(new Employee("Alin", "Ionescu", LocalDate.of(1984, 12, 5), Position.COOK));

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

    public List<Employee> findEmployeesByPosition(String position) {
        return employeeRepository.findByPosition(position);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        try {
            findEmployeeById(id);
            employee.setId(id);

            return saveEmployee(employee);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No such employee.");
        }
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
