package com.pizza.restcontroller;

import com.pizza.converter.PizzeriaConverter;
import com.pizza.dto.PizzeriaDto;
import com.pizza.entity.Pizzeria;
import com.pizza.service.PizzeriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/pizzeria")
@CrossOrigin(origins = "*")
public class PizzeriaRestController {

    private final PizzeriaService pizzeriaService;
    private final PizzeriaConverter pizzeriaConverter;

    @Autowired
    public PizzeriaRestController(PizzeriaService pizzeriaService, PizzeriaConverter pizzeriaConverter) {
        this.pizzeriaService = pizzeriaService;
        this.pizzeriaConverter = pizzeriaConverter;
    }

    @GetMapping
    public ResponseEntity<PizzeriaDto> findPizzeriaById(@RequestParam(name = "id", required = false) Long id,
                                                        @RequestParam(value = "name", required = false) String name) {
        Pizzeria pizzeria = new Pizzeria();

        if (id != null) {
            pizzeria = pizzeriaService.findPizzeriaById(id);
        } else if (name != null) {
            pizzeria = pizzeriaService.findPizzeriaByName(name);
        }

        return ResponseEntity.ok(pizzeriaConverter.convertFromEntityToDto(pizzeria));
    }

    @PostMapping
    public ResponseEntity<PizzeriaDto> addPizzeria(@Valid @RequestBody PizzeriaDto pizzeriaDto) {
        Pizzeria pizzeria = pizzeriaConverter.convertFromDtoToEntity(pizzeriaDto);
        Pizzeria savedPizzeria = pizzeriaService.savePizzeria(pizzeria);

        return ResponseEntity.ok(pizzeriaConverter.convertFromEntityToDto(savedPizzeria));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PizzeriaDto> addPizzeria(@PathVariable(name = "id") Long id, @Valid @RequestBody PizzeriaDto pizzeriaDto) {
        Pizzeria pizzeria = pizzeriaConverter.convertFromDtoToEntity(pizzeriaDto);
        Pizzeria updatedPizzeria = pizzeriaService.updatePizzeria(id, pizzeria);

        return ResponseEntity.ok(pizzeriaConverter.convertFromEntityToDto(updatedPizzeria));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<PizzeriaDto> deletePizzeriaById(@PathVariable Long id) {
        pizzeriaService.deletePizzeriaById(id);

        return ResponseEntity.noContent().build();
    }
}
