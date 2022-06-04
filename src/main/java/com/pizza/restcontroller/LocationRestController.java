package com.pizza.restcontroller;

import com.pizza.converter.LocationConverter;
import com.pizza.dto.LocationDto;
import com.pizza.entity.Location;
import com.pizza.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/locations")
@CrossOrigin(origins = "*")
public class LocationRestController {

    private final LocationService locationService;
    private final LocationConverter locationConverter;

    @Autowired
    public LocationRestController(LocationService locationService, LocationConverter locationConverter) {
        this.locationService = locationService;
        this.locationConverter = locationConverter;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<LocationDto>> findAllLocations(@RequestParam(name = "allLocations", required = false) Boolean allLocations,
                                                              @RequestParam(name = "address", required = false) String address) {
        List<Location> allLocationsList = new ArrayList<>();

        if (allLocations) {
            allLocationsList = locationService.findAllLocations();
        } else if (address != null) {
            allLocationsList = locationService.findLocationsByAddress(address);
        }

        return ResponseEntity.ok(locationConverter.convertFromEntityListToDtoList(allLocationsList));
    }

    @GetMapping
    public ResponseEntity<LocationDto> findLocation(@RequestParam(name = "id", required = false) Long id,
                                                    @RequestParam(name = "name", required = false) String name) {
        Location location = new Location();

        if (id != null) {
            location = locationService.findLocationById(id);
        } else if (name != null) {
            location = locationService.findLocationByName(name);
        }

        return ResponseEntity.ok(locationConverter.convertFromEntityToDto(location));
    }

    @PostMapping
    public ResponseEntity<LocationDto> addLocation(@RequestBody LocationDto locationDto) {
        Location location = locationConverter.convertFromDtoToEntity(locationDto);
        Location savedLocation = locationService.saveLocation(location);

        return ResponseEntity.ok(locationConverter.convertFromEntityToDto(savedLocation));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<LocationDto> updateLocation(@PathVariable Long id, @RequestBody LocationDto locationDto) {
        Location location = locationConverter.convertFromDtoToEntity(locationDto);
        Location updatedLocation = locationService.updateLocation(id, location);

        return ResponseEntity.ok(locationConverter.convertFromEntityToDto(updatedLocation));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<LocationDto> deleteLocationById(@PathVariable Long id) {
        locationService.deleteLocationById(id);

        return ResponseEntity.noContent().build();
    }
}
