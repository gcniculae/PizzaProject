package com.pizza.restcontroller;

import com.pizza.converter.LocationConverter;
import com.pizza.dto.LocationDto;
import com.pizza.entity.Location;
import com.pizza.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping
    public ResponseEntity<List<LocationDto>> findAllLocations(@RequestParam(name = "all", required = false, defaultValue = "false") Boolean all,
                                                              @RequestParam(name = "address", required = false) String address) {
        List<Location> allLocationsList = new ArrayList<>();

        if (all) {
            allLocationsList = locationService.findAllLocations();
        } else if (address != null) {
            allLocationsList = locationService.findLocationsByAddress(address);
        }

        List<LocationDto> locationDtoList = locationConverter.convertFromEntityListToDtoList(allLocationsList);
        locationService.addPizzeriaIdToDtoList(allLocationsList, locationDtoList);

        return ResponseEntity.ok(locationDtoList);
    }

    @GetMapping(path = "/single")
    public ResponseEntity<LocationDto> findLocation(@RequestParam(name = "id", required = false) Long id,
                                                    @RequestParam(name = "name", required = false) String name) {
        Location location = new Location();

        if (id != null) {
            location = locationService.findLocationById(id);
        } else if (name != null) {
            location = locationService.findLocationByName(name);
        }

        LocationDto locationDto = locationConverter.convertFromEntityToDto(location);
        locationService.addPizzeriaIdToDto(location, locationDto);

        return ResponseEntity.ok(locationDto);
    }

    @PostMapping
    public ResponseEntity<LocationDto> addLocation(@Valid @RequestBody LocationDto locationDto) {
        Location location = locationConverter.convertFromDtoToEntity(locationDto);
        Location savedLocation = locationService.saveLocation(location, locationDto.getPizzeriaId());
        LocationDto savedLocationDto = locationConverter.convertFromEntityToDto(savedLocation);
        locationService.addPizzeriaIdToDto(savedLocation, savedLocationDto);

        return ResponseEntity.ok(savedLocationDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<LocationDto> updateLocation(@PathVariable Long id, @Valid @RequestBody LocationDto locationDto) {
        Location location = locationConverter.convertFromDtoToEntity(locationDto);
        Location updatedLocation = locationService.updateLocation(id, location, locationDto.getPizzeriaId());
        LocationDto updatedLocationDto = locationConverter.convertFromEntityToDto(updatedLocation);
        locationService.addPizzeriaIdToDto(updatedLocation, updatedLocationDto);

        return ResponseEntity.ok(updatedLocationDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<LocationDto> deleteLocationById(@PathVariable Long id) {
        locationService.deleteLocationById(id);

        return ResponseEntity.noContent().build();
    }
}
