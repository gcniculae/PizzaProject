package com.pizza.converter;

import com.pizza.dto.LocationDto;
import com.pizza.entity.Location;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class LocationConverter extends BaseConverter<LocationDto, Location> {

    @Override
    public LocationDto convertFromEntityToDto(Location location) {
        LocationDto locationDto = new LocationDto();
        BeanUtils.copyProperties(location, locationDto, "location");
        locationDto.setPizzeriaId(location.getPizzeria().getId());

        return locationDto;
    }

    @Override
    public Location convertFromDtoToEntity(LocationDto locationDto) {
        Location location = new Location();
        BeanUtils.copyProperties(locationDto, location, "locationId");

        return location;
    }
}
