package com.pizza.service;

import com.pizza.entity.Employee;
import com.pizza.entity.Location;
import com.pizza.entity.Position;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.EmployeeRepository;
import com.pizza.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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

        initializeLocations();

        initializeEmployees();
    }

    public void initializeEmployees() {
        employee1 = new Employee.EmployeeBuilder()
                .setFirstName("Andrei")
                .setLastName("Ionescu")
                .setPhoneNumber("0723001001")
                .setDateOfBirth(LocalDate.of(1988, 3, 24))
                .setAddress("Ploiesti")
                .setPosition(Position.COOK)
                .build();
        employee1.setId(1L);
        employee1.setLocation(location1);

        employee2 = new Employee.EmployeeBuilder()
                .setFirstName("Ion")
                .setLastName("Popescu")
                .setPhoneNumber("0723001002")
                .setDateOfBirth(LocalDate.of(1985, 7, 9))
                .setAddress("Ploiesti")
                .setPosition(Position.CASHIER).build();
        employee2.setId(2L);
        employee2.setLocation(location1);

        employee3 = new Employee.EmployeeBuilder()
                .setFirstName("Alexandru").setLastName("Antonescu")
                .setPhoneNumber("0723001003")
                .setDateOfBirth(LocalDate.of(1991, 10, 15))
                .setAddress("Ploiesti")
                .setPosition(Position.DELIVERY)
                .build();
        employee3.setId(3L);
        employee3.setLocation(location1);

        employee4 = new Employee.EmployeeBuilder()
                .setFirstName("Ion")
                .setLastName("Constantinescu")
                .setPhoneNumber("0723001004")
                .setDateOfBirth(LocalDate.of(1979, 1, 30))
                .setAddress("Ploiesti")
                .setPosition(Position.WAITER)
                .build();
        employee4.setId(4L);
        employee4.setLocation(location1);
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

        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void findCooksWithAgeUnder35Test() {
        List<Employee> employeeList = Arrays.asList(employee1, employee3);

        when(employeeRepository.findCooksWithAgeUnder35()).thenReturn(employeeList);

        assertEquals(employeeList.size(), employeeService.findCooksWithAgeUnder35().size());

        verify(employeeRepository, times(1)).findCooksWithAgeUnder35();
    }

    @Test
    public void findExistentEmployeeByIdTest() {
        when(employeeRepository.findById(eq(1L))).thenReturn(Optional.of(employee1));

        Employee employeeById = employeeService.findEmployeeById(1L);

        assertEquals(employee1.getId(), employeeById.getId());

        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    public void findNonexistentEmployeeById() {
        when(employeeRepository.findById(eq(1L))).thenThrow(new NotFoundException("No such employee.", "employee.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> employeeService.findEmployeeById(1L));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), ("No such employee."));
    }

    @Test
    public void findEmployeesByIdTest() {
        List<Long> ids = Arrays.asList(employee1.getId(), employee4.getId());

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));
        when(employeeRepository.findById(4L)).thenReturn(Optional.of(employee4));

        assertEquals(ids.size(), employeeService.findEmployeesById(ids).size());
    }
}
