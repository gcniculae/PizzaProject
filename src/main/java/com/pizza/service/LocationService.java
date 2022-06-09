package com.pizza.service;

import com.pizza.entity.Location;
import com.pizza.exception.NotFoundException;
import com.pizza.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final PizzeriaService pizzeriaService;

    @Autowired
    public LocationService(LocationRepository locationRepository, PizzeriaService pizzeriaService) {
        this.locationRepository = locationRepository;
        this.pizzeriaService = pizzeriaService;
    }

    public Location saveLocation(Location location, Long pizzeriaId) {
        location.setPizzeria(pizzeriaService.findPizzeriaById(pizzeriaId));

        return locationRepository.save(location);
    }

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    public Location findLocationById(Long id) {
        Optional<Location> optionalLocation = locationRepository.findById(id);

        if (optionalLocation.isPresent()) {
            return optionalLocation.get();
        } else {
            throw new NotFoundException("No location client found.", "location.not.found");
        }
    }

    public Location findLocationByName(String name) {
        Optional<Location> optionalLocation = locationRepository.findByName(name);

        if (optionalLocation.isPresent()) {
            return optionalLocation.get();
        } else {
            throw new NotFoundException("No location client found.", "location.not.found");
        }
    }

    public List<Location> findLocationsByAddress(String address) {
        return locationRepository.findByAddressContainingIgnoreCase(address);
    }

    public Location updateLocation(Long id, Location location, Long pizzeriaId) {
        Location locationById = findLocationById(id);
        location.setId(locationById.getId());

        return saveLocation(location, pizzeriaId);
    }

    public void deleteLocationById(Long id) {
        locationRepository.deleteById(id);
    }
}
