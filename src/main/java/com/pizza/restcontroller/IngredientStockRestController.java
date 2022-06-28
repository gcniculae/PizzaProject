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
    public ResponseEntity<List<IngredientStockDto>> findAllIngredientStocks(@RequestParam(name = "all", required = false, defaultValue = "false") Boolean all,
                                                                            @RequestParam(name = "quantity", required = false) Long quantity,
                                                                            @RequestParam(name = "lowQuantity", required = false, defaultValue = "false") Boolean lowQuantity,
                                                                            @RequestParam(name = "expirationDate", required = false)
                                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expirationDate) {
        List<IngredientStock> allIngredientStocksList = new ArrayList<>();

        if (all) {
            allIngredientStocksList = ingredientStockService.findAllIngredientStocks();
        } else if (quantity != null) {
            allIngredientStocksList = ingredientStockService.findIngredientStocksByQuantity(quantity);
        } else if (lowQuantity) {
            allIngredientStocksList = ingredientStockService.findIngredientStocksByLowQuantity();
        } else if (expirationDate != null) {
            allIngredientStocksList = ingredientStockService.findIngredientStocksByExpirationDate(expirationDate);
        }

        List<IngredientStockDto> ingredientStockDtoList = ingredientStockConverter.convertFromEntityListToDtoList(allIngredientStocksList);
        ingredientStockService.addLocationIdToDtoList(allIngredientStocksList, ingredientStockDtoList);

        return ResponseEntity.ok(ingredientStockDtoList);
    }

    @GetMapping(path = "/single")
    public ResponseEntity<IngredientStockDto> findIngredientStock(@RequestParam(name = "id", required = false) Long id,
                                                                  @RequestParam(name = "name", required = false) String name) {
        IngredientStock ingredientStock = new IngredientStock();

        if (id != null) {
            ingredientStock = ingredientStockService.findIngredientStockById(id);
        } else if (name != null) {
            ingredientStock = ingredientStockService.findIngredientStockByName(name);
        }

        IngredientStockDto ingredientStockDto = ingredientStockConverter.convertFromEntityToDto(ingredientStock);
        ingredientStockService.addLocationIdToDto(ingredientStock, ingredientStockDto);

        return ResponseEntity.ok(ingredientStockDto);
    }

    @PostMapping
    public ResponseEntity<IngredientStockDto> addIngredientStocks(@Valid @RequestBody IngredientStockDto ingredientStockDto) {
        IngredientStock ingredientStock = ingredientStockConverter.convertFromDtoToEntity(ingredientStockDto);
        IngredientStock savedIngredientStock = ingredientStockService.saveIngredientStock(ingredientStock, ingredientStockDto.getLocationId());
        IngredientStockDto savedIngredientStockDto = ingredientStockConverter.convertFromEntityToDto(savedIngredientStock);
        ingredientStockService.addLocationIdToDto(savedIngredientStock, savedIngredientStockDto);

        return ResponseEntity.ok(savedIngredientStockDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<IngredientStockDto> updateIngredientStocks(@PathVariable Long id, @Valid @RequestBody IngredientStockDto ingredientStockDto) {
        IngredientStock ingredientStock = ingredientStockConverter.convertFromDtoToEntity(ingredientStockDto);
        IngredientStock updatedIngredientStock = ingredientStockService.updateIngredientStock(id, ingredientStock, ingredientStockDto.getLocationId());
        IngredientStockDto updateIngredientStockDto = ingredientStockConverter.convertFromEntityToDto(updatedIngredientStock);
        ingredientStockService.addLocationIdToDto(updatedIngredientStock, updateIngredientStockDto);

        return ResponseEntity.ok(updateIngredientStockDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<IngredientStockDto> deleteById(@PathVariable Long id) {
        ingredientStockService.deleteIngredientStockById(id);

        return ResponseEntity.noContent().build();
    }
}
