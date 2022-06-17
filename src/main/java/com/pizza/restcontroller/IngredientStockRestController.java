package com.pizza.restcontroller;

import com.pizza.converter.IngredientStockConverter;
import com.pizza.dto.IngredientStockDto;
import com.pizza.entity.IngredientStock;
import com.pizza.service.IngredientStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/ingredientStocks")
@CrossOrigin(origins = "*")
public class IngredientStockRestController {

    private final IngredientStockService ingredientStockService;
    private final IngredientStockConverter ingredientStockConverter;

    @Autowired
    public IngredientStockRestController(IngredientStockService ingredientStockService, IngredientStockConverter ingredientStockConverter) {
        this.ingredientStockService = ingredientStockService;
        this.ingredientStockConverter = ingredientStockConverter;
    }

    @GetMapping
    public ResponseEntity<List<IngredientStockDto>> findAllIngredientStocks(@RequestParam(name = "allIngredientStocks", required = false, defaultValue = "false") Boolean allIngredientStocks,
                                                                            @RequestParam(name = "quantity", required = false) Long quantity,
                                                                            @RequestParam(name = "lowQuantity", required = false) Boolean lowQuantity,
                                                                            @RequestParam(name = "expirationDate", required = false)
                                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expirationDate) {
        List<IngredientStock> allIngredientStocksList = new ArrayList<>();

        if (allIngredientStocks != null && allIngredientStocks) {
            allIngredientStocksList = ingredientStockService.findAllIngredientStocks();
        } else if (quantity != null) {
            allIngredientStocksList = ingredientStockService.findIngredientStockByQuantity(quantity);
        } else if (lowQuantity != null && lowQuantity) {
            allIngredientStocksList = ingredientStockService.findIngredientStocksByLowQuantity();
        } else if (expirationDate != null) {
            allIngredientStocksList = ingredientStockService.findIngredientStockByExpirationDate(expirationDate);
        }

        return ResponseEntity.ok(ingredientStockConverter.convertFromEntityListToDtoList(allIngredientStocksList));
    }

    @GetMapping(path = "/ingredientStock")
    public ResponseEntity<IngredientStockDto> findIngredientStock(@RequestParam(name = "id", required = false) Long id,
                                                                  @RequestParam(name = "name", required = false) String name) {
        IngredientStock ingredientStock = new IngredientStock();

        if (id != null) {
            ingredientStock = ingredientStockService.findIngredientStockById(id);
        } else if (name != null) {
            ingredientStock = ingredientStockService.findIngredientStocksByName(name);
        }

        return ResponseEntity.ok(ingredientStockConverter.convertFromEntityToDto(ingredientStock));
    }

    @PostMapping
    public ResponseEntity<IngredientStockDto> addIngredientStocks(@Valid @RequestBody IngredientStockDto ingredientStockDto) {
        IngredientStock ingredientStock = ingredientStockConverter.convertFromDtoToEntity(ingredientStockDto);
        IngredientStock savedIngredientStock = ingredientStockService.saveIngredientStock(ingredientStock, ingredientStockDto.getLocationId());

        return ResponseEntity.ok(ingredientStockConverter.convertFromEntityToDto(savedIngredientStock));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<IngredientStockDto> updateIngredientStocks(@PathVariable Long id, @Valid @RequestBody IngredientStockDto ingredientStockDto) {
        IngredientStock ingredientStock = ingredientStockConverter.convertFromDtoToEntity(ingredientStockDto);
        IngredientStock updatedIngredientStock = ingredientStockService.updateIngredientStock(id, ingredientStock, ingredientStockDto.getLocationId());

        return ResponseEntity.ok(ingredientStockConverter.convertFromEntityToDto(updatedIngredientStock));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<IngredientStockDto> deleteById(@PathVariable Long id) {
        ingredientStockService.deleteIngredientStockById(id);

        return ResponseEntity.noContent().build();
    }
}
