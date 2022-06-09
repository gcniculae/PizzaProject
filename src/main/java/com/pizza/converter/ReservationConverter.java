package com.pizza.converter;

import com.pizza.dto.ReservationDto;
import com.pizza.entity.Reservation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ReservationConverter extends BaseConverter<ReservationDto, Reservation> {

    @Override
    public ReservationDto convertFromEntityToDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        BeanUtils.copyProperties(reservation, reservationDto, "location");
        reservationDto.setLocationId(reservation.getLocation().getId());

        return reservationDto;
    }

    @Override
    public Reservation convertFromDtoToEntity(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationDto, reservation, "locationId");

        return reservation;
    }
}
