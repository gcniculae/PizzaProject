package com.pizza.repository;

import com.pizza.entity.Pizzeria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PizzeriaRepository extends JpaRepository<Pizzeria, Long> {

    Optional<Pizzeria> findByName(String name);
}
