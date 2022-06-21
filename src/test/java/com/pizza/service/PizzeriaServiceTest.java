package com.pizza.service;

import com.pizza.entity.Owner;
import com.pizza.entity.Pizzeria;
import com.pizza.repository.PizzeriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;

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
}
