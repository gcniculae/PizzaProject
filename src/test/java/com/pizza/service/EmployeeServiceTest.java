package com.pizza.service;

import com.pizza.dto.EmployeeDto;
import com.pizza.entity.Employee;
import com.pizza.entity.Location;
import com.pizza.entity.Position;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.domain.Specification;

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
    private Employee employee5;
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
                .setFirstName("Andrei")
                .setLastName("Popescu")
                .setPhoneNumber("0723001002")
                .setDateOfBirth(LocalDate.of(1988, 3, 24))
                .setAddress("Ploiesti")
                .setPosition(Position.CASHIER).build();
        employee2.setId(2L);
        employee2.setLocation(location1);

        employee3 = new Employee.EmployeeBuilder()
                .setFirstName("Alexandru")
                .setLastName("Antonescu")
                .setPhoneNumber("0723001003")
                .setDateOfBirth(LocalDate.of(1991, 10, 15))
                .setAddress("Ploiesti")
                .setPosition(Position.DELIVERY)
                .build();
        employee3.setId(3L);
        employee3.setLocation(location1);

        employee4 = new Employee.EmployeeBuilder()
                .setFirstName("Ion")
                .setLastName("Popescu")
                .setPhoneNumber("0723001004")
                .setDateOfBirth(LocalDate.of(1979, 1, 30))
                .setAddress("Ploiesti")
                .setPosition(Position.WAITER)
                .build();
        employee4.setId(4L);
        employee4.setLocation(location1);

        employee5 = new Employee.EmployeeBuilder()
                .setFirstName("Alexandru")
                .setLastName("Stefanescu")
                .setPhoneNumber("0728119422")
                .setDateOfBirth(LocalDate.of(1989, 4, 24))
                .setAddress("Ploiesti")
                .setPosition(Position.COOK)
                .build();
        employee5.setId(5L);
        employee5.setLocation(location1);
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

    @Test
    public void findEmployeesByFirstNameTest() {
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findByFirstName(eq(employee1.getFirstName()))).thenReturn(employees);

        assertEquals(employees.size(), employeeService.findEmployeesByFirstName(employee1.getFirstName()).size());

        verify(employeeRepository, times(1)).findByFirstName(employee1.getFirstName());
    }

    @Test
    public void findEmployeesByLastNameTest() {
        List<Employee> employees = Arrays.asList(employee2, employee4);

        when(employeeRepository.findByLastName(eq(employee1.getLastName()))).thenReturn(employees);

        assertEquals(employees.size(), employeeService.findEmployeesByLastName(employee1.getLastName()).size());

        verify(employeeRepository, times(1)).findByLastName(employee1.getLastName());
    }

    @Test
    public void findEmployeesByDateOfBirthTest() {
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findByDateOfBirth(eq(employee1.getDateOfBirth()))).thenReturn(employees);

        assertEquals(employees.size(), employeeService.findEmployeesByDateOfBirth(employee1.getDateOfBirth()).size());

        verify(employeeRepository, times(1)).findByDateOfBirth(employee1.getDateOfBirth());
    }

    @Test
    public void findEmployeesByPositionTest() {
        List<Employee> employees = Arrays.asList(employee1, employee5);

        when(employeeRepository.findByPosition(eq(employee1.getPosition()))).thenReturn(employees);

        assertEquals(employees.size(), employeeService.findEmployeesByPosition(employee1.getPosition()).size());

        verify(employeeRepository, times(1)).findByPosition(employee1.getPosition());
    }

    @Test
    public void findEmployeesBornInTimeframeTest() {
        List<Employee> employees = Arrays.asList(employee1, employee2, employee3, employee5);

        LocalDate startDate = LocalDate.of(1985, 1, 1);
        LocalDate endDate = LocalDate.of(1990, 1, 1);
        when(employeeRepository.findEmployeesBornInTimeframe(startDate, endDate)).thenReturn(employees);

        assertEquals(employees.size(), employeeService.findEmployeesBornInTimeframe(startDate, endDate).size());
    }

    @Test
    public void updateEmployeeTest() {
        when(employeeRepository.findById(eq(1L))).thenReturn(Optional.of(employee1));

        employee1.setPosition(Position.MANAGER);
        employee1.setAddress("Bucuresti");

        when(employeeRepository.save(eq(employee1))).thenReturn(employee1);

        Employee updatedEmployee = employeeService.updateEmployee(1L, employee1, location1.getId());

        assertEquals(employee1.getId(), updatedEmployee.getId());
        assertEquals(employee1.getPosition(), updatedEmployee.getPosition());
    }

    @Test
    public void deleteExistentEmployeeByIdTest() {
        when(employeeRepository.findById(eq(1L))).thenReturn(Optional.of(employee1));
        doNothing().when(employeeRepository).deleteById(eq(1L));

        employeeService.deleteEmployeeById(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteNonexistentEmployeeByIdTest() {
        when(employeeRepository.findById(eq(1L))).thenThrow(new NotFoundException("No such employee found.", "employee.not.found"));

        NotFoundException notFoundException = assertThrows(
                NotFoundException.class,
                () -> employeeService.findEmployeeById(1L));

        assertEquals(notFoundException.getMessage(), "No such employee found.");
    }

    @Test
    public void findEmployeesUsingSpecificationTest() {
        List<Employee> employees = Arrays.asList(employee1, employee2);

        EmployeeDto employeeDto = new EmployeeDto.EmployeeDtoBuilder()
                .setFirstNameForDto(employee1.getFirstName())
                .buildDto();

        EmployeeSpecification employeeFilter = new EmployeeSpecification();
        employeeFilter.add(new SearchCriteria("firstName", employeeDto.getFirstName(), SpecificationOperation.EQUAL, false));

        when(employeeRepository.findAll(Specification.where(any(EmployeeSpecification.class)))).thenReturn(employees);

        assertEquals(employees.size(), employeeService.findEmployeesUsingSpecification(employeeDto).size());
    }
}
