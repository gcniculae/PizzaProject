package com.pizza.service;

import com.pizza.entity.IngredientStock;
import com.pizza.entity.Location;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.IngredientStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class IngredientStockService {

    private final IngredientStockRepository ingredientStockRepository;
    private final LocationService locationService;

    @Autowired
    public IngredientStockService(IngredientStockRepository ingredientStockRepository, LocationService locationService) {
        this.ingredientStockRepository = ingredientStockRepository;
        this.locationService = locationService;
    }

    public IngredientStock saveIngredientStock(IngredientStock ingredientStock, Long locationId) {
        ingredientStock.setLocation(locationService.findLocationById(locationId));

        return ingredientStockRepository.save(ingredientStock);
    }

    public List<IngredientStock> findAllIngredientStocks() {
        return ingredientStockRepository.findAll();
    }

    public IngredientStock findIngredientStockById(Long id) {
        Optional<IngredientStock> optionalIngredientStock = ingredientStockRepository.findById(id);

        if (optionalIngredientStock.isPresent()) {
            return optionalIngredientStock.get();
        } else {
            throw new NotFoundException("No such ingredient stock found.", "ingredientStock.not.found");
        }
    }

    public IngredientStock findIngredientStocksByName(String name) {
        Optional<IngredientStock> optionalIngredientStock = ingredientStockRepository.findByName(name);

        if (optionalIngredientStock.isPresent()) {
            return optionalIngredientStock.get();
        } else {
            throw new NotFoundException("No such ingredient stock found.", "ingredientStock.not.found");
        }
    }

    public List<IngredientStock> findIngredientStockByQuantity(Long quantity) {
        return ingredientStockRepository.findByQuantity(quantity);
    }

    public List<IngredientStock> findIngredientStockByExpirationDate(LocalDate expirationDate) {
        return ingredientStockRepository.findByExpirationDate(expirationDate);
    }

    public IngredientStock updateIngredientStock(Long id, IngredientStock ingredientStock, Long locationId) {
        IngredientStock ingredientStockById = findIngredientStockById(id);
        ingredientStock.setId(id);

        return saveIngredientStock(ingredientStock, locationId);
    }

    public void deleteIngredientStockById(Long id) {
        ingredientStockRepository.deleteById(id);
    }
}
