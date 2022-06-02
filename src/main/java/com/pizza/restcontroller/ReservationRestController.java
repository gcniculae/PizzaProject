package com.pizza.restcontroller;

import com.pizza.service.ReservationService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationRestController {

    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
}
