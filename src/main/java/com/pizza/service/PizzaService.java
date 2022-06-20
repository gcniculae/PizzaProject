package com.pizza.service;

import com.pizza.entity.Pizza;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
            throw new NotFoundException("No such pizza found.", "pizza.not.found");
        }
    }

    public Pizza findPizzaByName(String name) {
        Optional<Pizza> optionalPizza = pizzaRepository.findByName(name);

        if (optionalPizza.isPresent()) {
            return optionalPizza.get();
        } else {
            throw new NotFoundException("No such pizza found.", "pizza.not.found");
        }
    }

    public List<Pizza> findPizzasByPrice(Double price) {
        return pizzaRepository.findByPrice(price);
    }

    public Pizza updatePizza(Long id, Pizza pizza) {
        Pizza pizzaById = findPizzaById(id);
        pizza.setId(pizzaById.getId());

        return savePizza(pizza);
    }

    public void deletePizzaById(Long id) {
        Pizza pizzaById = findPizzaById(id);

        pizzaRepository.deleteById(pizzaById.getId());
    }

    @Transactional
    public void deletePizzaByName(String name) {
        pizzaRepository.deleteByName(name);
    }
}
