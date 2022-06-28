package com.pizza.service;

import com.pizza.dto.PizzeriaDto;
import com.pizza.entity.Pizzeria;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.PizzeriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PizzeriaService {

    private final PizzeriaRepository pizzeriaRepository;
    private final OwnerService ownerService;

    @Autowired
    public PizzeriaService(PizzeriaRepository pizzeriaRepository, OwnerService ownerService) {
        this.pizzeriaRepository = pizzeriaRepository;
        this.ownerService = ownerService;
    }

    public Pizzeria savePizzeria(Pizzeria pizzeria, Long ownerId) {
        pizzeria.setOwner(ownerService.findOwnerById(ownerId));

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

    public Pizzeria updatePizzeria(Long id, Pizzeria pizzeria, Long ownerId) {
        Pizzeria pizzeriaById = findPizzeriaById(id);
        pizzeria.setId(pizzeriaById.getId());

        return savePizzeria(pizzeria, ownerId);
    }

    public void deletePizzeriaById(Long id) {
        Pizzeria pizzeriaById = findPizzeriaById(id);

        pizzeriaRepository.deleteById(pizzeriaById.getId());
    }

    public void addOwnerIdToDto(Pizzeria pizzeria, PizzeriaDto pizzeriaDto) {
        pizzeriaDto.setOwnerId(pizzeria.getOwner().getId());
    }
}
