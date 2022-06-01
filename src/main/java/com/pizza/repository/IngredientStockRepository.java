package com.pizza.repository;

import com.pizza.entity.IngredientStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientStockRepository extends JpaRepository<IngredientStock, Long> {

    Optional<IngredientStock> findByName(String name);

    List<IngredientStock> findByQuantity(int quantity);

    List<IngredientStock> findByExpirationDate(LocalDate expirationDate);
}
