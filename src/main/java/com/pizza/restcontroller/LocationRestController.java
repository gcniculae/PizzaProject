package com.pizza.restcontroller;

import com.pizza.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/location")
@CrossOrigin(origins = "*")
public class LocationRestController {

    private final LocationService locationService;

    @Autowired
    public LocationRestController(LocationService locationService) {
        this.locationService = locationService;
    }
}
