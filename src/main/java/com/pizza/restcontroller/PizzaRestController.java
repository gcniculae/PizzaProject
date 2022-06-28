package com.pizza.restcontroller;

import com.pizza.converter.PizzaConverter;
import com.pizza.dto.PizzaDto;
import com.pizza.entity.Pizza;
import com.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/pizzas")
@CrossOrigin(origins = "*")
public class PizzaRestController {

    private final PizzaService pizzaService;
    private final PizzaConverter pizzaConverter;

    @Autowired
    public PizzaRestController(PizzaService pizzaService, PizzaConverter pizzaConverter) {
        this.pizzaService = pizzaService;
        this.pizzaConverter = pizzaConverter;
    }

    @GetMapping
    public ResponseEntity<List<PizzaDto>> findAllPizzas(@RequestParam(name = "all", required = false, defaultValue = "false") Boolean all,
                                                        @RequestParam(name = "price", required = false) Double price) {
        List<Pizza> allPizzasList = new ArrayList<>();

        if (all) {
            allPizzasList = pizzaService.findAllPizza();
        } else if (price != null) {
            allPizzasList = pizzaService.findPizzasByPrice(price);
        }

        return ResponseEntity.ok(pizzaConverter.convertFromEntityListToDtoList(allPizzasList));
    }

    @GetMapping(path = "/single")
    public ResponseEntity<PizzaDto> findPizza(@RequestParam(name = "id", required = false) Long id,
                                              @RequestParam(name = "name", required = false) String name) {
        Pizza pizza = new Pizza();

        if (id != null) {
            pizza = pizzaService.findPizzaById(id);
        } else if (name != null) {
            pizza = pizzaService.findPizzaByName(name);
        }

        return ResponseEntity.ok(pizzaConverter.convertFromEntityToDto(pizza));
    }

    @PostMapping
    public ResponseEntity<PizzaDto> addPizza(@Valid @RequestBody PizzaDto pizzaDto) {
        Pizza pizza = pizzaConverter.convertFromDtoToEntity(pizzaDto);
        Pizza savedPizza = pizzaService.savePizza(pizza);

        return ResponseEntity.ok(pizzaConverter.convertFromEntityToDto(savedPizza));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PizzaDto> updatePizza(@PathVariable(name = "id") Long id, @Valid @RequestBody PizzaDto pizzaDto) {
        Pizza pizza = pizzaConverter.convertFromDtoToEntity(pizzaDto);
        Pizza updatedPizza = pizzaService.updatePizza(id, pizza);

        return ResponseEntity.ok(pizzaConverter.convertFromEntityToDto(updatedPizza));
    }

    @DeleteMapping
    public ResponseEntity<PizzaDto> deletePizzaById(@RequestParam(name = "id", required = false) Long id,
                                                    @RequestParam(name = "name", required = false) String name) {
        if (id != null) {
            pizzaService.deletePizzaById(id);
        } else if (name != null) {
            pizzaService.deletePizzaByName(name);
        }

        return ResponseEntity.noContent().build();
    }
}
