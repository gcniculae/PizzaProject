package com.pizza.service;

import com.pizza.entity.Owner;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final PizzeriaService pizzeriaService;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, PizzeriaService pizzeriaService) {
        this.ownerRepository = ownerRepository;
        this.pizzeriaService = pizzeriaService;
    }

    public Owner saveOwner(Owner owner, Long pizzeriaId) {
        owner.setPizzeria(pizzeriaService.findPizzeriaById(pizzeriaId));

        return ownerRepository.save(owner);
    }

    public Owner findOwnerById(Long id) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);

        if (optionalOwner.isPresent()) {
            return optionalOwner.get();
        } else {
            throw new NotFoundException("No such owner found.", "owner.not.found");
        }
    }

    public Owner updateOwner(Long id, Owner owner, Long pizzeriaId) {
        Owner ownerById = findOwnerById(id);
        owner.setId(ownerById.getId());

        return saveOwner(owner, pizzeriaId);
    }

    public void deleteOwnerById(Long id) {
        ownerRepository.deleteById(id);
    }

    @Transactional
    public void deleteOwnerByFirstNameAndLastName(String firstName, String lastName) {
        ownerRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }
}
