package com.pizza.transformer;

import com.pizza.dto.ReservationDto;
import com.pizza.entity.Reservation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ReservationTransformer {

    public ReservationDto transformFromReservationToReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        BeanUtils.copyProperties(reservation, reservationDto);

        return reservationDto;
    }

    public Reservation transformFromReservationDtoToReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationDto, reservation);

        return reservation;
    }
}
