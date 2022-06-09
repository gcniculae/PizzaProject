package com.pizza.repository;

import com.pizza.entity.Employee;
import com.pizza.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByLastName(String lastName);

    List<Employee> findByDateOfBirth(LocalDate dateOfBirth);

    List<Employee> findByPosition(Position position);

    @Query(nativeQuery = true, value = "SELECT * FROM employee WHERE position = 1 and YEAR(date_of_birth) > YEAR(NOW()) - 35;")
    List<Employee> findCooksWithAgeUnder35();

    @Query(value = "from Employee e where e.dateOfBirth between :startDate and :endDate")
    List<Employee> findEmployeesBornInTimeframe(LocalDate startDate, LocalDate endDate);
}
