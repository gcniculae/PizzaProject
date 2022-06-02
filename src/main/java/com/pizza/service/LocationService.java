package com.pizza.service;

import com.pizza.entity.Location;
import com.pizza.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location saveLocation(Location location) {
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
            throw new NoSuchElementException("No such location.");
        }
    }

    public Location findLocationByName(String name) {
        Optional<Location> optionalLocation = locationRepository.findByName(name);

        if (optionalLocation.isPresent()) {
            return optionalLocation.get();
        } else {
            throw new NoSuchElementException("No such location.");
        }
    }

    public void deleteLocationById(Long id) {
        locationRepository.deleteById(id);
    }
}
