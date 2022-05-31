package com.pizza.repository;

import com.pizza.entity.IngredientStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientStockRepository extends JpaRepository<IngredientStock, Long> {

}
