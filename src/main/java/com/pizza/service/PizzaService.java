package com.pizza.service;

import com.pizza.entity.Pizza;
import com.pizza.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public Pizza savePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public List<Pizza> findAllPizza() {
        return pizzaRepository.findAll();
    }

    public Pizza findPizzaById(Long id) {
        Optional<Pizza> optionalPizza = pizzaRepository.findById(id);

        if (optionalPizza.isPresent()) {
            return optionalPizza.get();
        } else {
            throw new NoSuchElementException("No such pizza.");
        }
    }

    public Pizza findPizzaByName(String name) {
        Optional<Pizza> optionalPizza = pizzaRepository.findByName(name);

        if (optionalPizza.isPresent()) {
            return optionalPizza.get();
        } else {
            throw new NoSuchElementException("No such pizza.");
        }
    }

    public Pizza updatePizza(Long id, Pizza pizza) {
        Pizza pizzaById = findPizzaById(id);
        pizza.setId(pizzaById.getId());

        return savePizza(pizza);
    }

    public void deletePizzaById(Long id) {
        pizzaRepository.deleteById(id);
    }

    public void deletePizzaByName(String name) {
        pizzaRepository.deleteByName(name);
    }
}
