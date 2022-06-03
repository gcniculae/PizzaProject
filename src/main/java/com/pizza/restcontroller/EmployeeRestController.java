package com.pizza.restcontroller;

import com.pizza.dto.EmployeeDto;
import com.pizza.entity.Employee;
import com.pizza.service.EmployeeService;
import com.pizza.converter.EmployeeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
@CrossOrigin(origins = "*")
public class EmployeeRestController {

    private final EmployeeService employeeService;
    private final EmployeeConverter employeeTransformer;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, EmployeeConverter employeeTransformer) {
        this.employeeService = employeeService;
        this.employeeTransformer = employeeTransformer;
    }

    @GetMapping(path = "/all-employees")
    public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
        List<Employee> allEmployees = employeeService.findAllEmployees();
        List<EmployeeDto> allEmployeesDto = allEmployees.stream()
                .map(employeeTransformer::transformFromEntityToDto).toList();

        return ResponseEntity.ok(allEmployeesDto);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        EmployeeDto employeeDto = employeeTransformer.transformFromEntityToDto(employee);

        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping(path = "/first-name/{firstName}")
    public ResponseEntity<List<EmployeeDto>> findEmployeesByFirstName(@PathVariable("firstName") String firstName) {
        List<Employee> employees = employeeService.findEmployeesByFirstName(firstName);
        List<EmployeeDto> employeesDto = employees.stream()
                .map(employeeTransformer::transformFromEntityToDto).toList();

        return ResponseEntity.ok(employeesDto);
    }

    @GetMapping(path = "/last-name/{lastName}")
    public ResponseEntity<List<EmployeeDto>> findEmployeesByLastName(@PathVariable("lastName") String lastName) {
        List<Employee> employees = employeeService.findEmployeesByLastName(lastName);
        List<EmployeeDto> employeesDto = employees.stream()
                .map(employeeTransformer::transformFromEntityToDto).toList();

        return ResponseEntity.ok(employeesDto);
    }

    @GetMapping(path = "/date-of-birth/{dateOfBirth}")
    public ResponseEntity<List<EmployeeDto>> findEmployeesByDateOfBirth(@PathVariable("dateOfBirth") LocalDate dateOfBirth) {
        List<Employee> employees = employeeService.findEmployeesByDateOfBirth(dateOfBirth);
        List<EmployeeDto> employeesDto = employees.stream()
                .map(employeeTransformer::transformFromEntityToDto).toList();

        return ResponseEntity.ok(employeesDto);
    }

    @GetMapping(path = "/position/{position}")
    public ResponseEntity<List<EmployeeDto>> findEmployeesByPosition(@PathVariable("position") String position) {
        List<Employee> employees = employeeService.findEmployeesByPosition(position);
        List<EmployeeDto> employeesDto = employees.stream()
                .map(employeeTransformer::transformFromEntityToDto).toList();

        return ResponseEntity.ok(employeesDto);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeTransformer.transformFromDtoToEntity(employeeDto);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        EmployeeDto savedEmployeeDto = employeeTransformer.transformFromEntityToDto(savedEmployee);

        return ResponseEntity.ok(savedEmployeeDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeTransformer.transformFromDtoToEntity(employeeDto);
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        EmployeeDto updatedEmployeeDto = employeeTransformer.transformFromEntityToDto(updatedEmployee);

        return ResponseEntity.ok(updatedEmployeeDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);

        return ResponseEntity.noContent().build();
    }
}
