package com.pizza.restcontroller;

import com.pizza.dto.EmployeeDto;
import com.pizza.entity.Employee;
import com.pizza.entity.Position;
import com.pizza.service.EmployeeService;
import com.pizza.converter.EmployeeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @GetMapping(path = "/all")
    public ResponseEntity<List<EmployeeDto>> findAllEmployees(@RequestParam(name = "allEmployees", required = false) Boolean allEmployees,
                                                              @RequestParam(name = "firstName", required = false) String firstName,
                                                              @RequestParam(name = "lastName", required = false) String lastName,
                                                              @RequestParam(name = "dateOfBirth", required = false)
                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                                                              @RequestParam(name = "position", required = false) Position position,
                                                              @RequestParam(name = "under35", required = false) Boolean under35,
                                                              @RequestParam(name = "startDate", required = false)
                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                              @RequestParam(name = "endDate", required = false)
                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Employee> allEmployeesList = new ArrayList<>();

        if (allEmployees != null && allEmployees) {
            allEmployeesList = employeeService.findAllEmployees();
        } else if (firstName != null) {
            allEmployeesList = employeeService.findEmployeesByFirstName(firstName);
        } else if (lastName != null) {
            allEmployeesList = employeeService.findEmployeesByLastName(lastName);
        } else if (dateOfBirth != null) {
            allEmployeesList = employeeService.findEmployeesByDateOfBirth(dateOfBirth);
        } else if (position != null) {
            allEmployeesList = employeeService.findEmployeesByPosition(position);
        } else if (under35 != null && under35) {
            allEmployeesList = employeeService.findCooksWithAgeUnder35();
        } else if (startDate != null && endDate != null) {
            allEmployeesList = employeeService.findEmployeesBornInTimeframe(startDate, endDate);
        }

        return ResponseEntity.ok(employeeTransformer.convertFromEntityListToDtoList(allEmployeesList));
    }

//    @GetMapping(path = "/all-employees")
//    public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
//        List<Employee> allEmployees = employeeService.findAllEmployees();
//        List<EmployeeDto> allEmployeesDto = allEmployees.stream()
//                .map(employeeTransformer::transformFromEntityToDto).toList();
//
//        return ResponseEntity.ok(allEmployeesDto);
//    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        EmployeeDto employeeDto = employeeTransformer.convertFromEntityToDto(employee);

        return ResponseEntity.ok(employeeDto);
    }

//    @GetMapping(path = "/first-name/{firstName}")
//    public ResponseEntity<List<EmployeeDto>> findEmployeesByFirstName(@PathVariable("firstName") String firstName) {
//        List<Employee> employees = employeeService.findEmployeesByFirstName(firstName);
//        List<EmployeeDto> employeesDto = employees.stream()
//                .map(employeeTransformer::transformFromEntityToDto).toList();
//
//        return ResponseEntity.ok(employeesDto);
//    }
//
//    @GetMapping(path = "/last-name/{lastName}")
//    public ResponseEntity<List<EmployeeDto>> findEmployeesByLastName(@PathVariable("lastName") String lastName) {
//        List<Employee> employees = employeeService.findEmployeesByLastName(lastName);
//        List<EmployeeDto> employeesDto = employees.stream()
//                .map(employeeTransformer::transformFromEntityToDto).toList();
//
//        return ResponseEntity.ok(employeesDto);
//    }
//
//    @GetMapping(path = "/date-of-birth/{dateOfBirth}")
//    public ResponseEntity<List<EmployeeDto>> findEmployeesByDateOfBirth(@PathVariable("dateOfBirth") LocalDate dateOfBirth) {
//        List<Employee> employees = employeeService.findEmployeesByDateOfBirth(dateOfBirth);
//        List<EmployeeDto> employeesDto = employees.stream()
//                .map(employeeTransformer::transformFromEntityToDto).toList();
//
//        return ResponseEntity.ok(employeesDto);
//    }
//
//    @GetMapping(path = "/position/{position}")
//    public ResponseEntity<List<EmployeeDto>> findEmployeesByPosition(@PathVariable("position") String position) {
//        List<Employee> employees = employeeService.findEmployeesByPosition(position);
//        List<EmployeeDto> employeesDto = employees.stream()
//                .map(employeeTransformer::transformFromEntityToDto).toList();
//
//        return ResponseEntity.ok(employeesDto);
//    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeTransformer.convertFromDtoToEntity(employeeDto);
        Employee savedEmployee = employeeService.saveEmployee(employee, employeeDto.getLocationId());
        EmployeeDto savedEmployeeDto = employeeTransformer.convertFromEntityToDto(savedEmployee);

        return ResponseEntity.ok(savedEmployeeDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeTransformer.convertFromDtoToEntity(employeeDto);
        Employee updatedEmployee = employeeService.updateEmployee(id, employee, employeeDto.getLocationId());
        EmployeeDto updatedEmployeeDto = employeeTransformer.convertFromEntityToDto(updatedEmployee);

        return ResponseEntity.ok(updatedEmployeeDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);

        return ResponseEntity.noContent().build();
    }
}
