package com.pizza.service;


import com.pizza.entity.Location;
import com.pizza.entity.Pizzeria;
import com.pizza.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceTest {

    @InjectMocks
    private LocationService locationService;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private PizzeriaService pizzeriaService;

    private Location location1;
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

    
}
