package com.pizza.transformer;

import com.pizza.dto.LocationDto;
import com.pizza.entity.Location;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class LocationTransformer {

    public LocationDto transformFromLocationToLocationDto(Location location) {
        LocationDto locationDto = new LocationDto();
        BeanUtils.copyProperties(location, locationDto);

        return locationDto;
    }

    public Location transformFromLocationDtoToLocation(LocationDto locationDto) {
        Location location = new Location();
        BeanUtils.copyProperties(locationDto, location);

        return location;
    }
}
