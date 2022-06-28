package com.pizza.service;

import com.pizza.dto.EmployeeDto;
import com.pizza.entity.Location;
import com.pizza.repository.SpecificationOperation;
import com.pizza.entity.Employee;
import com.pizza.entity.Position;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.EmployeeRepository;
import com.pizza.repository.EmployeeSpecification;
import com.pizza.repository.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final LocationService locationService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, LocationService locationService) {
        this.employeeRepository = employeeRepository;
        this.locationService = locationService;
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

    public Employee saveEmployee(Employee employee, Long locationId) {
        employee.setLocation(locationService.findLocationById(locationId));

        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> findCooksWithAgeUnder35() {
        return employeeRepository.findCooksWithAgeUnder35();
    }

    public List<Employee> findEmployeesBornInTimeframe(LocalDate startDate, LocalDate endDate) {
        return employeeRepository.findEmployeesBornInTimeframe(startDate, endDate);
    }

    public Employee findEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            throw new NotFoundException("No such employee found.", "employee.not.found");
        }
    }

    public List<Employee> findEmployeesById(List<Long> ids) {
        List<Employee> employees = new ArrayList<>();

        for (Long id : ids) {
            employees.add(findEmployeeById(id));
        }

        return employees;
    }

    public List<Employee> findEmployeesUsingSpecification(EmployeeDto employeeDto) {
        EmployeeSpecification employeeFilter = new EmployeeSpecification();

        if (employeeDto.getFirstName() != null) {
            employeeFilter.add(new SearchCriteria("firstName", employeeDto.getFirstName(), SpecificationOperation.EQUAL, false));
        } else if (employeeDto.getLastName() != null) {
            employeeFilter.add(new SearchCriteria("lastName", employeeDto.getLastName(), SpecificationOperation.EQUAL, false));
        } else if (employeeDto.getPhoneNumber() != null) {
            employeeFilter.add(new SearchCriteria("phoneNumber", employeeDto.getPhoneNumber(), SpecificationOperation.EQUAL, false));
        } else if (employeeDto.getDateOfBirth() != null) {
            employeeFilter.add(new SearchCriteria("dateOfBirth", employeeDto.getDateOfBirth(), SpecificationOperation.EQUAL, true));
        } else if (employeeDto.getAddress() != null) {
            employeeFilter.add(new SearchCriteria("address", employeeDto.getAddress(), SpecificationOperation.EQUAL, false));
        } else if (employeeDto.getPosition() != null) {
            employeeFilter.add(new SearchCriteria("position", employeeDto.getPosition(), SpecificationOperation.EQUAL, false));
        } else if (employeeDto.getLocationId() != null) {
            employeeFilter.add(new SearchCriteria("location", locationService.findLocationById(employeeDto.getLocationId()), SpecificationOperation.EQUAL, false));
        }

        return employeeRepository.findAll(Specification.where(employeeFilter));
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

    public Employee updateEmployee(Long id, Employee employee, Long locationId) {
        Employee employeeById = findEmployeeById(id);
        employee.setId(employeeById.getId());

        return saveEmployee(employee, locationId);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public void addLocationIdToDto(Location location, EmployeeDto employeeDto) {
        employeeDto.setLocationId(location.getId());
    }

    public void addLocationIdToDtoList(List<Employee> allEmployeesList, List<EmployeeDto> employeeDtoList) {
        for (int index = 0; index < employeeDtoList.size(); index++) {
            employeeDtoList.get(index).setLocationId(allEmployeesList.get(index).getLocation().getId());
        }
    }
}
