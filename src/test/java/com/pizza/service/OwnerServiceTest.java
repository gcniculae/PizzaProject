package com.pizza.service;

import com.pizza.entity.Owner;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.OwnerRepository;
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
public class OwnerServiceTest {

    @InjectMocks
    private OwnerService ownerService;

    @Mock
    private OwnerRepository ownerRepository;

    private Owner owner;

    @BeforeEach
    public void init() {
        ownerRepository = mock(OwnerRepository.class);
        ownerService = new OwnerService(ownerRepository);

        initializeOwner();
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

    @Test
    public void saveOwnerTest() {
        when(ownerRepository.save(eq(owner))).thenReturn(owner);

        Owner savedOwner = ownerService.saveOwner(owner);

        assertEquals(owner.getId(), savedOwner.getId());

        verify(ownerRepository, times(1)).save(owner);
    }

    @Test
    public void findExistentOwnerByIdTest() {
        when(ownerRepository.findById(eq(owner.getId()))).thenReturn(Optional.of(owner));

        Owner ownerById = ownerService.findOwnerById(owner.getId());

        assertEquals(ownerById.getId(), ownerById.getId());

        verify(ownerRepository, times(1)).findById(owner.getId());
    }

    @Test
    public void findNonexistentOwnerByIdTest() {
        when(ownerRepository.findById(eq(owner.getId()))).thenThrow(new NotFoundException("No such owner found.", "owner.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> ownerRepository.findById(owner.getId()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such owner found.");
    }

    @Test
    public void updateOwnerTest() {
        when(ownerRepository.findById(eq(owner.getId()))).thenReturn(Optional.of(owner));
        owner.setPhoneNumber("0728819023");
        when(ownerRepository.save(eq(owner))).thenReturn(owner);

        Owner updatedOwner = ownerService.updateOwner(owner.getId(), owner);

        assertEquals(owner.getId(), updatedOwner.getId());
        assertEquals(owner.getPhoneNumber(), updatedOwner.getPhoneNumber());
    }

    @Test
    public void deleteExistentOwnerByIdTest() {
        when(ownerRepository.findById(eq(owner.getId()))).thenReturn(Optional.of(owner));
        doNothing().when(ownerRepository).deleteById(owner.getId());

        ownerService.deleteOwnerById(owner.getId());

        verify(ownerRepository, times(1)).deleteById(owner.getId());
    }

    @Test
    public void deleteNonexistentOwnerByIdTest() {
        when(ownerRepository.findById(eq(owner.getId()))).thenThrow(new NotFoundException("No such owner found.", "owner.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> ownerRepository.findById(owner.getId()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such owner found.");
    }

    @Test
    public void deleteExistentOwnerByFirstNameAndLastNameTest() {
        when(ownerRepository.findByFirstNameAndLastName(eq(owner.getFirstName()), eq(owner.getLastName()))).thenReturn(Optional.of(owner));
        doNothing().when(ownerRepository).deleteByFirstNameAndLastName(owner.getFirstName(), owner.getFirstName());

        ownerService.deleteOwnerByFirstNameAndLastName(owner.getFirstName(), owner.getLastName());

        verify(ownerRepository, times(1)).deleteByFirstNameAndLastName(owner.getFirstName(), owner.getLastName());
    }

    @Test
    public void deleteNonexistentOwnerByFirstNameAndLastNameTest() {
        when(ownerRepository.findByFirstNameAndLastName(eq(owner.getFirstName()), eq(owner.getLastName())))
                .thenThrow(new NotFoundException("No such owner found.", "owner.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> ownerRepository.findByFirstNameAndLastName(owner.getFirstName(), owner.getLastName()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such owner found.");
    }
}
