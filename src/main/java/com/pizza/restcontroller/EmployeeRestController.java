package com.pizza.restcontroller;

import com.pizza.dto.EmployeeDto;
import com.pizza.entity.Employee;
import com.pizza.service.EmployeeService;
import com.pizza.transformer.EmployeeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/employee")
@CrossOrigin(origins = "*")
public class EmployeeRestController {

    private final EmployeeService employeeService;
    private final EmployeeTransformer employeeTransformer;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, EmployeeTransformer employeeTransformer) {
        this.employeeService = employeeService;
        this.employeeTransformer = employeeTransformer;
    }

    @GetMapping(path = "allEmployees")
    public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
        List<Employee> allEmployees = employeeService.findAllEmployees();
        List<EmployeeDto> allEmployeesDto = allEmployees.stream()
                .map(employeeTransformer::transformFromEmployeeToEmployeeDto).toList();

        return ResponseEntity.ok(allEmployeesDto);
    }

    @GetMapping(path = "employeeId/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        EmployeeDto employeeDto = employeeTransformer.transformFromEmployeeToEmployeeDto(employee);

        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping(path = "employeeFirstName/{firstName}")
    public ResponseEntity<List<EmployeeDto>> findEmployeesByFirstName(@PathVariable("firstName") String firstName) {
        List<Employee> employees = employeeService.findEmployeesByFirstName(firstName);
        List<EmployeeDto> employeesDto = employees.stream()
                .map(employeeTransformer::transformFromEmployeeToEmployeeDto).toList();

        return ResponseEntity.ok(employeesDto);
    }

    @GetMapping(path = "employeeLastName/{lastName}")
    public ResponseEntity<List<EmployeeDto>> findEmployeesByLastName(@PathVariable("lastName") String lastName) {
        List<Employee> employees = employeeService.findEmployeesByLastName(lastName);
        List<EmployeeDto> employeesDto = employees.stream()
                .map(employeeTransformer::transformFromEmployeeToEmployeeDto).toList();

        return ResponseEntity.ok(employeesDto);
    }

    @GetMapping(path = "employeeDateOfBirth/{dateOfBirth}")
    public ResponseEntity<List<EmployeeDto>> findEmployeesByDateOfBirth(@PathVariable("dateOfBirth") LocalDate dateOfBirth) {
        List<Employee> employees = employeeService.findEmployeesByDateOfBirth(dateOfBirth);
        List<EmployeeDto> employeesDto = employees.stream()
                .map(employeeTransformer::transformFromEmployeeToEmployeeDto).toList();

        return ResponseEntity.ok(employeesDto);
    }

    @GetMapping(path = "employeePosition/{position}")
    public ResponseEntity<List<EmployeeDto>> findEmployeesByPosition(@PathVariable("position") String position) {
        List<Employee> employees = employeeService.findEmployeesByPosition(position);
        List<EmployeeDto> employeesDto = employees.stream()
                .map(employeeTransformer::transformFromEmployeeToEmployeeDto).toList();

        return ResponseEntity.ok(employeesDto);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeTransformer.transformFromEmployeeDtoToEmployee(employeeDto);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        EmployeeDto savedEmployeeDto = employeeTransformer.transformFromEmployeeToEmployeeDto(savedEmployee);

        return ResponseEntity.ok(savedEmployeeDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
        Employee employee = employeeTransformer.transformFromEmployeeDtoToEmployee(employeeDto);
        Employee savedEmployee = employeeService.updateEmployee(employee, id);
        EmployeeDto savedEmployeeDto = employeeTransformer.transformFromEmployeeToEmployeeDto(savedEmployee);

        return ResponseEntity.ok(savedEmployeeDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);

        return ResponseEntity.noContent().build();
    }
}
