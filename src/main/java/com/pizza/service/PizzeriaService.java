package com.pizza.service;

import com.pizza.entity.Pizzeria;
import com.pizza.repository.PizzeriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PizzeriaService {

    private final PizzeriaRepository pizzeriaRepository;

    @Autowired
    public PizzeriaService(PizzeriaRepository pizzeriaRepository) {
        this.pizzeriaRepository = pizzeriaRepository;
    }

    public void savePizzeria(Pizzeria pizzeria) {
        pizzeriaRepository.save(pizzeria);
    }

    public List<Pizzeria> findAllPizzerias() {
        return pizzeriaRepository.findAll();
    }

    public Pizzeria findPizzeriaById(Long id) {
        Optional<Pizzeria> optionalPizzeria = pizzeriaRepository.findById(id);

        if (optionalPizzeria.isPresent()) {
            return optionalPizzeria.get();
        } else {
            throw new NoSuchElementException("No such pizzeria.");
        }
    }

    public void deletePizzeria(Pizzeria pizzeria) {
        pizzeriaRepository.delete(pizzeria);
    }

    public void deletePizzeriaById(Long id) {
        pizzeriaRepository.deleteById(id);
    }
}
