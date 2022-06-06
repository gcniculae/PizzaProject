package com.pizza.service;

import com.pizza.entity.Employee;
import com.pizza.entity.Position;
import com.pizza.exception.NotFoundException;
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

//    @PostConstruct
//    public void createEmployees() {
//        List<Employee> initialEmployees = new ArrayList<>();
//        initialEmployees.add(new Employee.EmployeeBuilder().setFirstName("Andrei").setLastName("Ionescu").setPhoneNumber("0723001001").setDateOfBirth(LocalDate.of(1970, 3, 24)).setAddress("Ploiesti").setPosition(Position.COOK).build());
//        initialEmployees.add(new Employee.EmployeeBuilder().setFirstName("Ion").setLastName("Popescu").setPhoneNumber("0723001002").setDateOfBirth(LocalDate.of(1985, 7, 9)).setAddress("Ploiesti").setPosition(Position.CASHIER).build());
//        initialEmployees.add(new Employee.EmployeeBuilder().setFirstName("Alexandru").setLastName("Antonescu").setPhoneNumber("0723001003").setDateOfBirth(LocalDate.of(1991, 10, 15)).setAddress("Ploiesti").setPosition(Position.DELIVERY).build());
//        initialEmployees.add(new Employee.EmployeeBuilder().setFirstName("Ion").setLastName("Constantinescu").setPhoneNumber("0723001004").setDateOfBirth(LocalDate.of(1979, 1, 30)).setAddress("Ploiesti").setPosition(Position.WAITER).build());
//        initialEmployees.add(new Employee.EmployeeBuilder().setFirstName("Alin").setLastName("Ionescu").setPhoneNumber("0723001005").setDateOfBirth(LocalDate.of(1984, 12, 5)).setAddress("Ploiesti").setPosition(Position.COOK).build());
//
//        employeeRepository.saveAll(initialEmployees);
//    }

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
            throw new NotFoundException("No such employee found.", "employee.not.found");
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
