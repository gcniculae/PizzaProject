package com.pizza.service;

import com.pizza.entity.Owner;
import com.pizza.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @PostConstruct
    public void createOwner() {
//        Owner owner = new Owner("Andrei", "Andreescu", LocalDate.of(1978, 5, 19), );
    }

    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Owner findOwnerById(Long id) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);

        if (optionalOwner.isPresent()) {
            return optionalOwner.get();
        } else {
            throw new NoSuchElementException("No such owner.");
        }
    }

    public Owner updateOwner(Long id, Owner owner) {
        Owner ownerById = findOwnerById(id);
        owner.setId(ownerById.getId());

        return saveOwner(owner);
    }

    public void deleteOwnerById(Long id) {
        ownerRepository.deleteById(id);
    }

    @Transactional
    public void deleteOwnerByFirstNameAndLastName(String firstName, String lastName) {
        ownerRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }
}
