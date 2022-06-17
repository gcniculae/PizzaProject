package com.pizza.service;

import com.pizza.entity.Employee;
import com.pizza.entity.Location;
import com.pizza.entity.Position;
import com.pizza.repository.EmployeeRepository;
import com.pizza.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private LocationService locationService;

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private Employee employee4;
    private Location location1;

    @BeforeEach
    public void init() {
        employeeRepository = mock(EmployeeRepository.class);
        locationService = mock(LocationService.class);
        employeeService = new EmployeeService(employeeRepository, locationService);

        initializeEmployees();

        initializeLocations();
    }

    public void initializeEmployees() {
        employee1 = new Employee.EmployeeBuilder()
                .setFirstName("Andrei")
                .setLastName("Ionescu")
                .setPhoneNumber("0723001001")
                .setDateOfBirth(LocalDate.of(1970, 3, 24))
                .setAddress("Ploiesti")
                .setPosition(Position.COOK)
                .build();

        employee2 = new Employee.EmployeeBuilder()
                .setFirstName("Ion")
                .setLastName("Popescu")
                .setPhoneNumber("0723001002")
                .setDateOfBirth(LocalDate.of(1985, 7, 9))
                .setAddress("Ploiesti")
                .setPosition(Position.CASHIER).build();

        employee3 = new Employee.EmployeeBuilder()
                .setFirstName("Alexandru").setLastName("Antonescu")
                .setPhoneNumber("0723001003")
                .setDateOfBirth(LocalDate.of(1991, 10, 15))
                .setAddress("Ploiesti")
                .setPosition(Position.DELIVERY)
                .build();

        employee4 = new Employee.EmployeeBuilder()
                .setFirstName("Ion")
                .setLastName("Constantinescu")
                .setPhoneNumber("0723001004")
                .setDateOfBirth(LocalDate.of(1979, 1, 30))
                .setAddress("Ploiesti")
                .setPosition(Position.WAITER)
                .build();
    }

    private void initializeLocations() {
        location1 = new Location();
        location1.setId(1L);
        location1.setName("Location1");
    }

    @Test
    public void saveEmployeeTest() {
        when(employeeRepository.save(employee1)).thenReturn(employee1);

        Employee savedEmployee = employeeService.saveEmployee(employee1, location1.getId());

        assertThat(savedEmployee.getId()).isEqualTo(employee1.getId());

        verify(employeeRepository, times(1)).save(employee1);
    }

    @Test
    public void findAllEmployeesTest() {
        List<Employee> employeeList = Arrays.asList(employee1, employee2, employee3, employee4);

        when(employeeRepository.findAll()).thenReturn(employeeList);

        assertEquals(employeeList.size(), employeeService.findAllEmployees().size());

        verify(employeeRepository).findAll();
    }
}
