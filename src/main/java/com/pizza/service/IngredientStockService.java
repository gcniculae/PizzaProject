package com.pizza.service;

import com.pizza.entity.IngredientStock;
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

    @Autowired
    public IngredientStockService(IngredientStockRepository ingredientStockRepository) {
        this.ingredientStockRepository = ingredientStockRepository;
    }

    public IngredientStock saveIngredientStock(IngredientStock ingredientStock) {
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
            throw new NoSuchElementException("No such ingredient stock.");
        }
    }

    public IngredientStock findIngredientStocksByName(String name) {
        Optional<IngredientStock> optionalIngredientStock = ingredientStockRepository.findByName(name);

        if (optionalIngredientStock.isPresent()) {
            return optionalIngredientStock.get();
        } else {
            throw new NoSuchElementException("No such ingredient stock.");
        }
    }

    public List<IngredientStock> findIngredientStockByQuantity(int quantity) {
        return ingredientStockRepository.findByQuantity(quantity);
    }

    public List<IngredientStock> findIngredientStockByExpirationDate(LocalDate expirationDate) {
        return ingredientStockRepository.findByExpirationDate(expirationDate);
    }

    public IngredientStock updateIngredientStock(Long id, IngredientStock ingredientStock) {
        IngredientStock ingredientStockById = findIngredientStockById(id);
        ingredientStock.setId(id);

        return saveIngredientStock(ingredientStock);
    }

    public void deleteIngredientStockById(Long id) {
        ingredientStockRepository.deleteById(id);
    }
}
