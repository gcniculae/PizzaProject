package com.pizza.repository;

import com.pizza.entity.Client;
import com.pizza.entity.Employee;
import com.pizza.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByLastName(String lastName);

    List<Employee> findByDateOfBirth(LocalDate dateOfBirth);

    List<Employee> findByPosition(Position position);
}
