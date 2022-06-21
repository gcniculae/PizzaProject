package com.pizza.service;

import com.pizza.entity.Owner;
import com.pizza.entity.Pizzeria;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.PizzeriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PizzeriaServiceTest {

    @InjectMocks
    private PizzeriaService pizzeriaService;

    @Mock
    private PizzeriaRepository pizzeriaRepository;

    @Mock
    private OwnerService ownerService;

    private Pizzeria pizzeria;
    private Owner owner;

    @BeforeEach
    public void init() {
        pizzeriaRepository = mock(PizzeriaRepository.class);
        ownerService = mock(OwnerService.class);
        pizzeriaService = new PizzeriaService(pizzeriaRepository, ownerService);

        initializeOwner();

        initializePizzeria();
    }

    private void initializeOwner() {
        owner = new Owner.OwnerBuilder()
                .setFirstName("Andrei")
                .setLastName("Popescu")
                .setPhoneNumber("0729111200")
                .setDateOfBirth(LocalDate.of(1978, 5, 19))
                .setAddress("Ploiesti")
                .build();
        owner.setId(1L);
    }

    private void initializePizzeria() {
        pizzeria = new Pizzeria.PizzeriaBuilder()
                .setName("Pizzeria")
                .setOwner(owner)
                .build();
    }

    @Test
    public void savePizzeriaTest() {
        when(pizzeriaRepository.save(eq(pizzeria))).thenReturn(pizzeria);
        when(ownerService.findOwnerById(eq(owner.getId()))).thenReturn(owner);

        Pizzeria savedPizzeria = pizzeriaService.savePizzeria(pizzeria, owner.getId());

        assertEquals(pizzeria.getId(), savedPizzeria.getId());

        verify(pizzeriaRepository, times(1)).save(pizzeria);
    }

    @Test
    public void findExistentPizzeriaByIdTest() {
        when(pizzeriaRepository.findById(eq(pizzeria.getId()))).thenReturn(Optional.of(pizzeria));

        Pizzeria pizzeriaById = pizzeriaService.findPizzeriaById(pizzeria.getId());

        assertEquals(pizzeria.getId(), pizzeriaById.getId());

        verify(pizzeriaRepository, times(1)).findById(pizzeria.getId());
    }

    @Test
    public void findNonexistentPizzeriaByIdTest() {
        when(pizzeriaRepository.findById(eq(pizzeria.getId()))).thenThrow(new NotFoundException("No such pizzeria found.", "pizzeria.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> pizzeriaRepository.findById(pizzeria.getId()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such pizzeria found.");
    }

    @Test
    public void findExistentPizzeriaByNameTest() {
        when(pizzeriaRepository.findByName(eq(pizzeria.getName()))).thenReturn(Optional.of(pizzeria));

        Pizzeria pizzeriaByName = pizzeriaService.findPizzeriaByName(pizzeria.getName());

        assertEquals(pizzeria.getName(), pizzeriaByName.getName());

        verify(pizzeriaRepository, times(1)).findByName(pizzeria.getName());
    }

    @Test
    public void findNonexistentPizzeriaByNameTest() {
        when(pizzeriaRepository.findByName(eq(pizzeria.getName()))).thenThrow(new NotFoundException("No such pizzeria found.", "pizzeria.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> pizzeriaRepository.findByName(pizzeria.getName()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such pizzeria found.");
    }

    @Test
    public void updatePizzeriaTest() {
        when(pizzeriaRepository.findById(eq(pizzeria.getId()))).thenReturn(Optional.of(pizzeria));
        pizzeria.setName("PizzeriaAAA");
        when(ownerService.findOwnerById(eq(owner.getId()))).thenReturn(owner);
        when(pizzeriaRepository.save(eq(pizzeria))).thenReturn(pizzeria);

        Pizzeria updatedPizzeria = pizzeriaService.updatePizzeria(pizzeria.getId(), pizzeria, owner.getId());

        assertEquals(pizzeria.getId(), updatedPizzeria.getId());
        assertEquals(pizzeria.getName(), updatedPizzeria.getName());
    }

    @Test
    public void deleteExistentPizzeriaById() {
        when(pizzeriaRepository.findById(eq(pizzeria.getId()))).thenReturn(Optional.of(pizzeria));
        doNothing().when(pizzeriaRepository).deleteById(pizzeria.getId());

        pizzeriaService.deletePizzeriaById(pizzeria.getId());

        verify(pizzeriaRepository).deleteById(pizzeria.getId());
    }

    @Test
    public void deleteNonexistentPizzeriaByIdTest() {
        when(pizzeriaRepository.findById(eq(pizzeria.getId()))).thenThrow(new NotFoundException("No such pizzeria found.", "pizzeria.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> pizzeriaRepository.findById(pizzeria.getId()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such pizzeria found.");
    }
}
