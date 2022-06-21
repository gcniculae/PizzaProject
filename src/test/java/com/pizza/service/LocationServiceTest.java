package com.pizza.service;


import com.pizza.entity.Location;
import com.pizza.entity.Pizzeria;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceTest {

    @InjectMocks
    private LocationService locationService;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private PizzeriaService pizzeriaService;

    private Location location1;
    private Location location2;
    private Pizzeria pizzeria;

    @BeforeEach
    public void init() {
        locationRepository = mock(LocationRepository.class);
        pizzeriaService = mock(PizzeriaService.class);
        locationService = new LocationService(locationRepository, pizzeriaService);

        initializePizzeria();

        initializeLocations();
    }

    private void initializeLocations() {
        location1 = new Location();
        location1.setName("Location1");
        location1.setAddress("Ploiesti");
        location1.setId(1L);

        location2 = new Location();
        location2.setName("Location2");
        location2.setAddress("Ploiesti");
        location2.setId(2L);
    }

    private void initializePizzeria() {
        pizzeria = new Pizzeria.PizzeriaBuilder()
                .setName("Pizzeria")
                .build();
        pizzeria.setId(1L);
    }

    @Test
    public void saveLocationTest() {
        when(locationRepository.save(eq(location1))).thenReturn(location1);
        when(pizzeriaService.findPizzeriaById(eq(pizzeria.getId()))).thenReturn(pizzeria);

        Location savedLocation = locationService.saveLocation(location1, pizzeria.getId());

        assertEquals(location1.getId(), savedLocation.getId());
    }

    @Test
    public void findAllLocationsTest() {
        List<Location> locations = Arrays.asList(location1, location2);

        when(locationRepository.findAll()).thenReturn(locations);

        assertEquals(locations.size(), locationService.findAllLocations().size());

        verify(locationRepository).findAll();
    }

    @Test
    public void findExistentLocationByIdTest() {
        when(locationRepository.findById(eq(location1.getId()))).thenReturn(Optional.of(location1));

        Location locationById = locationService.findLocationById(location1.getId());

        assertEquals(location1.getId(), locationById.getId());

        verify(locationRepository).findById(location1.getId());
    }

    @Test
    public void findNonexistentLocationByIdTest() {
        when(locationRepository.findById(eq(location1.getId()))).thenThrow(new NotFoundException("No such location found.", "location.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> locationService.findLocationById(location1.getId()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such location found.");
    }

    @Test
    public void findLocationByNameTest() {
        when(locationRepository.findByName(eq(location1.getName()))).thenReturn(Optional.of(location1));

        Location locationByName = locationService.findLocationByName(location1.getName());

        assertEquals(location1.getId(), locationByName.getId());
        assertEquals(location1.getName(), locationByName.getName());

        verify(locationRepository).findByName(locationByName.getName());
    }

    @Test
    public void findLocationsByAddressTest() {
        List<Location> locations = Arrays.asList(location1, location2);

        when(locationRepository.findByAddressContainingIgnoreCase(eq(location1.getAddress()))).thenReturn(locations);

        List<Location> locationsByAddress = locationService.findLocationsByAddress(location1.getAddress());

        assertEquals(locations.size(), locationsByAddress.size());

        verify(locationRepository).findByAddressContainingIgnoreCase(location1.getAddress());
    }

    @Test
    public void updateLocationTest() {
        when(locationRepository.findById(eq(location1.getId()))).thenReturn(Optional.of(location1));
        location1.setAddress("Bucuresti");
        when(pizzeriaService.findPizzeriaById(eq(pizzeria.getId()))).thenReturn(pizzeria);
        when(locationRepository.save(eq(location1))).thenReturn(location1);

        Location updatedLocation = locationService.updateLocation(location1.getId(), location1, pizzeria.getId());

        assertEquals(location1.getId(), updatedLocation.getId());
    }

    @Test
    public void deleteExistentLocation() {
        when(locationRepository.findById(location1.getId())).thenReturn(Optional.of(location1));
        doNothing().when(locationRepository).deleteById(location1.getId());

        locationService.deleteLocationById(location1.getId());

        verify(locationRepository, times(1)).deleteById(location1.getId());
    }

    @Test
    public void deleteNonexistentLocation() {
        when(locationRepository.findById(location1.getId())).thenThrow(new NotFoundException("No such location found.", "location.not.found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> locationService.deleteLocationById(location1.getId()));

        assertNotNull(notFoundException);
        assertEquals(notFoundException.getMessage(), "No such location found.");
    }
}
