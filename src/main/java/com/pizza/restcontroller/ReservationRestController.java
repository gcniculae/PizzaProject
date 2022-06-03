package com.pizza.restcontroller;

import com.pizza.converter.ReservationConverter;
import com.pizza.service.ReservationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/reservations")
@CrossOrigin(origins = "*")
public class ReservationRestController {

    private final ReservationService reservationService;
    private final ReservationConverter reservationConverter;

    public ReservationRestController(ReservationService reservationService, ReservationConverter reservationConverter) {
        this.reservationService = reservationService;
        this.reservationConverter = reservationConverter;
    }
}
