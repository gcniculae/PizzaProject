package com.pizza.service;

import com.pizza.entity.Pizzeria;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.PizzeriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PizzeriaService {

    private final PizzeriaRepository pizzeriaRepository;

    @Autowired
    public PizzeriaService(PizzeriaRepository pizzeriaRepository) {
        this.pizzeriaRepository = pizzeriaRepository;
    }

    public Pizzeria savePizzeria(Pizzeria pizzeria) {
        return pizzeriaRepository.save(pizzeria);
    }

    public Pizzeria findPizzeriaById(Long id) {
        Optional<Pizzeria> optionalPizzeria = pizzeriaRepository.findById(id);

        if (optionalPizzeria.isPresent()) {
            return optionalPizzeria.get();
        } else {
            throw new NotFoundException("No such pizzeria found.", "pizzeria.not.found");
        }
    }

    public Pizzeria findPizzeriaByName(String name) {
        Optional<Pizzeria> optionalPizzeria = pizzeriaRepository.findByName(name);

        if (optionalPizzeria.isPresent()) {
            return optionalPizzeria.get();
        } else {
            throw new NotFoundException("No such pizzeria found.", "pizzeria.not.found");
        }
    }

    public Pizzeria updatePizzeria(Long id, Pizzeria pizzeria) {
        Pizzeria pizzeriaById = findPizzeriaById(id);
        pizzeria.setId(pizzeriaById.getId());

        return savePizzeria(pizzeria);
    }

    public void deletePizzeriaById(Long id) {
        pizzeriaRepository.deleteById(id);
    }
}
